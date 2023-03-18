package com.example.jokeappeasycoderu

import com.example.jokeappeasycoderu.data.CacheDataSource
import com.example.jokeappeasycoderu.data.JokeRealmModel

interface Joke {
    fun toJoke(): JokeUiModel
    fun toFavoriteJoke(): JokeUiModel.FavoriteJoke
    suspend fun change(cacheDataSource: CacheDataSource): JokeUiModel
    fun toJokeRealm(): JokeRealmModel

    class Base(
        private val id: Int,
        private val type: String,
        private val text: String,
        private val punchline: String
    ) : Joke {

        override fun toJoke(): JokeUiModel = JokeUiModel.Base(text, punchline)

        override fun toFavoriteJoke(): JokeUiModel.FavoriteJoke =
            JokeUiModel.FavoriteJoke(text, punchline)

        override suspend fun change(cacheDataSource: CacheDataSource): JokeUiModel =
            cacheDataSource.addOrRemove(id, this)

        override fun toJokeRealm(): JokeRealmModel
            = JokeRealmModel().also {
                it.id = id
                it.type = type
                it.text = text
                it.punchLine = punchline

        }

    }
}