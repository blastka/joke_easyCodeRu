package com.example.jokeappeasycoderu

import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CacheDataSource {
    suspend fun getJoke(): Result<Joke, Unit>
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel

    class Base(private val realmProvider: RealmProvider) : CacheDataSource {

        override suspend fun getJoke(): Result<Joke, Unit> {
            realmProvider.provide().use {
                val jokes = it.where(JokeRealm::class.java).findAll()
                if (jokes.isEmpty())
                    return Result.Error(Unit)
                else
                    jokes.random().let { joke ->
                        return Result.Success(
                            Joke.Base(
                                joke.id,
                                joke.type,
                                joke.text,
                                joke.punchLine
                            )
                        )
                    }
            }
        }

        override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
            return withContext(Dispatchers.IO) {
                Realm.getDefaultInstance().use {
                    val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                    if (jokeRealm == null) {
                        it.executeTransaction { transition ->
                            val newJoke = joke.toJokeRealm()
                            transition.insert(newJoke)
                        }
                        return@withContext joke.toFavoriteJoke()
                    } else {
                        it.executeTransaction {
                            jokeRealm.deleteFromRealm()
                        }
                        return@withContext joke.toJoke()
                    }
                }
            }
        }

    }
}