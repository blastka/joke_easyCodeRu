package com.example.jokeappeasycoderu.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: JokeDataModelMapper<JokeRealmModel>
) : CacheDataSource {
    override suspend fun getJoke(): JokeDataModel {
        realmProvider.provide().use {
            val jokes = it.where(JokeRealmModel::class.java).findAll()
            if (jokes.isEmpty())
                throw NoCachedJokesException()
            else
                return jokes.random().to()
        }
    }

    override suspend fun addOrRemove(id: Int, joke: JokeDataModel):
            JokeDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val jokeRealm =
                    it.where(JokeRealmModel::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
                    it.executeTransaction { transaction ->
                        val newJoke = joke.map(mapper)
                        transaction.insert(newJoke)
                    }
                    joke.changeCached(true)
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.changeCached(false)
                }
            }
        }
}