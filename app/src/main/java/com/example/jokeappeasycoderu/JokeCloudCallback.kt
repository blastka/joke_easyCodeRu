package com.example.jokeappeasycoderu

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(errorType: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}