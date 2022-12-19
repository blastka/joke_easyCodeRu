package com.example.jokeappeasycoderu

import com.example.jokeappeasycoderu.testCases.JokeRealm
import io.realm.Realm

interface CacheDataSource {
    fun getJoke(jokeCacheCallback: JokeCacheCallback)
    fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke

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
                            ServerModel.JokeServerModel(
                            joke.id,
                            joke.type,
                            joke.text,
                            joke.punchLine
                        ))
                    }
            }
        }

        override fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke {
            realm.let {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return if (jokeRealm == null){
                    val newJoke = jokeServerModel.toJokeRealm()
                    it.executeTransactionAsync { transition ->
                        transition.insert(newJoke)
                    }
                    jokeServerModel.toFavoriteJoke()
                } else {
                    it.executeTransactionAsync {
                        jokeRealm.deleteFromRealm()
                    }
                    jokeServerModel.toJoke()
                }
            }
        }

    }
}