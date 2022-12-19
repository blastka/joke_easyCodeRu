package com.example.jokeappeasycoderu

import com.google.gson.annotations.SerializedName

interface JokeServerModel {
    fun toJoke(): Joke

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("type")
        private val type: String,
        @SerializedName("setup")
        private val text: String,
        @SerializedName("punchline")
        private val punchline: String
    ) : JokeServerModel {
        override fun toJoke(): Joke =
            Joke.Base(id, type, text, punchline)

    }

}