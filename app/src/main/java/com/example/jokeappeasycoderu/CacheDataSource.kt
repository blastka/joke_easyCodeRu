package com.example.jokeappeasycoderu

interface CacheDataSource {
    fun getJoke(jokeCacheCallback: JokeCacheCallback)
    fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke
}