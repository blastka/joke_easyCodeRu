package com.example.jokeappeasycoderu.data

interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}