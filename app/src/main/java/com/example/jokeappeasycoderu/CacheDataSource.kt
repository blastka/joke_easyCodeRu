package com.example.jokeappeasycoderu

interface CacheDataSource {
    fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke
}