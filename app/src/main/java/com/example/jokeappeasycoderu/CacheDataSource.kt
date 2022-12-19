package com.example.jokeappeasycoderu

import io.realm.Realm

interface CacheDataSource {
    fun getJoke(jokeCacheCallback: JokeCacheCallback)
    fun addOrRemove(id: Int, joke: Joke): JokeUiModel

    class Base(private val realm: Realm) : CacheDataSource{
        override fun getJoke(jokeCacheCallback: JokeCacheCallback) {
            realm.let {
                val jokes = it.where(JokeRealm::class.java).findAll()
                if (jokes.isEmpty())
                    jokeCacheCallback.fail()
                else
                    jokes.random().let {
                        joke ->
                        jokeCacheCallback.provide(
                            Joke.Base(
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