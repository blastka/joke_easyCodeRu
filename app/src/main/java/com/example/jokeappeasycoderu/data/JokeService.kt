package com.example.jokeappeasycoderu.data

import retrofit2.http.GET

interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    suspend fun getJoke(): JokeServerModel.Base
}