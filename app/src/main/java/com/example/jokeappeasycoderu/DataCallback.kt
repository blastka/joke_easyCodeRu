package com.example.jokeappeasycoderu

import androidx.annotation.DrawableRes

interface DataCallback {
    fun provideText(text: String)
    fun provideIconRes(@DrawableRes id: Int)
}