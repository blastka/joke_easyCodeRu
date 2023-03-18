package com.example.jokeappeasycoderu

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}