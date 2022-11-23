package com.example.jokeappeasycoderu

interface Joke {
    fun getJokeUi(): String

    class Base(private val text: String, private val punchline: String) : Joke {
        override fun getJokeUi(): String {
            return "$text\n$punchline"
        }
    }
}