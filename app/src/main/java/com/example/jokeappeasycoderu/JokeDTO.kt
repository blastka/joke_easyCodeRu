package com.example.jokeappeasycoderu

import com.google.gson.annotations.SerializedName

interface DataJoke{
    fun toJoke(): Joke
}

data class JokeDTO (
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName ("setup")
    private val text: String,
    @SerializedName("punchline")
    private val punchline: String
    ) : DataJoke {
    override fun toJoke() = Joke.Base(text, punchline)
}