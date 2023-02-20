package com.example.jokeappeasycoderu

import android.util.Log

interface Joke {
    fun getJokeUi(): String

    /**
     * Один класс для успеха
     */
    class Base(private val text: String, private val punchline: String) : Joke {
        override fun getJokeUi(): String {
            return "$text\n$punchline"
        }
    }
}