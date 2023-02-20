package com.example.jokeappeasycoderu

import retrofit2.Call
import retrofit2.http.GET

/**
 * Ретрофит должен быть интерфейсом
 * В возврате Call должен быть дата класс с SerializedName полями
 */

interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeDTO>
}