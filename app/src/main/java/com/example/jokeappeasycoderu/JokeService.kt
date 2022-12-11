package com.example.jokeappeasycoderu

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeDTO>
}