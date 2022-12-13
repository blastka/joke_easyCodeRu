package com.example.jokeappeasycoderu

import androidx.annotation.DrawableRes

interface Joke {
    fun getJokeUi(): String
    fun map(callback: DataCallback)

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

    abstract class Abstract(private val text: String, private val punchline: String) : Joke {
        override fun getJokeUi(): String {
            return "$text\n$punchline"
        }

        override fun map(callback: DataCallback) {
            callback.run {
                provideText(getJokeUi())
                provideIconRes(getIconResId())
            }
        }

        @DrawableRes
        abstract fun getIconResId(): Int
    }
}