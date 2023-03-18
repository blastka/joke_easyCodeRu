package com.example.jokeappeasycoderu

import androidx.annotation.DrawableRes

sealed class State {
    object Progress: State()
    data class Initial(val text: String, @DrawableRes val id : Int)
}