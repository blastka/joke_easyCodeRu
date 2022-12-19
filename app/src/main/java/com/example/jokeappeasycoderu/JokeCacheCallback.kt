package com.example.jokeappeasycoderu

interface JokeCacheCallback {
    fun provide(jokeServerModel: Joke)
    fun fail()
}