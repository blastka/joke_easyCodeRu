package com.example.jokeappeasycoderu

interface JokeCloudCallback {
    fun provide(joke: ServerModel.JokeServerModel)
    fun fail(error: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}