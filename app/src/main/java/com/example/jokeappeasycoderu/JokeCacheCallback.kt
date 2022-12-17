package com.example.jokeappeasycoderu

interface JokeCacheCallback {
    fun provide(jokeServerModel: ServerModel.JokeServerModel)
    fun fail()
}