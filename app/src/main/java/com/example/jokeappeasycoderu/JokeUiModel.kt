package com.example.jokeappeasycoderu

import androidx.annotation.DrawableRes

interface JokeUiModel {
    fun getJokeUi(): String

    class Base(private val text: String, private val punchline: String) :
        Abstract(text, punchline) {
        override fun getIconResId(): Int {
            return R.drawable.ic_baseline_favorite_border_24
        }
    }

    class FavoriteJoke(private val text: String, private val punchline: String) :
        Abstract(text, punchline) {
        override fun getIconResId(): Int {
            return R.drawable.ic_baseline_favorite_24
        }
    }

    class Failed(text: String) : Abstract(text, "") {
        override fun getIconResId(): Int {
            return 0
        }
    }

    abstract class Abstract(private val text: String, private val punchline: String) :
        JokeUiModel {
        override fun getJokeUi(): String {
            return "$text\n$punchline"
        }

        @DrawableRes
        abstract fun getIconResId(): Int
    }
}