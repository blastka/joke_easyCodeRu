package com.example.jokeappeasycoderu.data

interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}