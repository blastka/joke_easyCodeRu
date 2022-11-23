package com.example.jokeappeasycoderu

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringRes: Int): String

    class Base(private val context: Context): ResourceManager{
        override fun getString(stringRes: Int): String {
            return context.getString(stringRes)
        }

    }
}

