package com.example.jokeappeasycoderu.data

import com.google.gson.annotations.SerializedName

interface JokeServerModel {
    fun toJokeDataModel(): JokeDataModel

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
        override fun toJokeDataModel(): JokeDataModel =
            JokeDataModel(id, type, text, punchline)

    }

}