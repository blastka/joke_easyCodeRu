package com.example.jokeappeasycoderu

import com.example.jokeappeasycoderu.testCases.JokeRealm
import com.google.gson.annotations.SerializedName

interface ServerModel {
    fun toJoke(): Joke
    fun toFavoriteJoke(): Joke.FavoriteJoke
    fun change(cacheDataSource: CacheDataSource): Joke
    fun toJokeRealm(): JokeRealm

    data class JokeServerModel(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("type")
        private val type: String,
        @SerializedName("setup")
        private val text: String,
        @SerializedName("punchline")
        private val punchline: String
    ) : ServerModel {
        override fun toJoke() = Joke.Base(text, punchline)

        override fun toFavoriteJoke(): Joke.FavoriteJoke =
            Joke.FavoriteJoke(text, punchline)

        override fun change(cacheDataSource: CacheDataSource): Joke {
            return cacheDataSource.addOrRemove(id, this)
        }

        override fun toJokeRealm(): JokeRealm {
            return JokeRealm().also {
                it.id = id
                it.type = type
                it.text = text
                it.punchLine = punchline
            }
        }


    }
}