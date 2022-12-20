package com.example.jokeappeasycoderu

import io.realm.Realm

interface CacheDataSource {
    suspend fun getJoke(): Result<Joke, Unit>
    fun addOrRemove(id: Int, joke: Joke): JokeUiModel

    class Base(private val realm: Realm) : CacheDataSource{

        override suspend fun getJoke(): Result<Joke, Unit> {
            realm.let {
                val jokes = it.where(JokeRealm::class.java).findAll()
                if (jokes.isEmpty())
                    return Result.Error(Unit)
                else
                    jokes.random().let {
                        joke ->
                        return Result.Success(Joke.Base(
                            joke.id,
                            joke.type,
                            joke.text,
                            joke.punchLine
                        ))
                    }
            }
        }

        override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
            realm.let {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return if (jokeRealm == null){
                    val newJoke = joke.toJokeRealm()
                    it.executeTransactionAsync { transition ->
                        transition.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else {
                    it.executeTransactionAsync {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toJoke()
                }
            }
        }

    }
}