package com.example.jokeappeasycoderu

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)

    class Base(private val service: JokeService) : CloudDataSource {
        override fun getJoke(jokeCloudCallback: JokeCloudCallback) {
            service.getJoke().enqueue(object : retrofit2.Callback<JokeServerModel> {
                override fun onResponse(
                    call: Call<JokeServerModel>,
                    response: Response<JokeServerModel>
                ) {
                    if (response.isSuccessful)
                        jokeCloudCallback.provide(response.body()!!.toJoke())
                    else
                        jokeCloudCallback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }

                override fun onFailure(call: Call<JokeServerModel>, t: Throwable) {
                    val errorType = if (t is UnknownHostException)
                        ErrorType.NO_CONNECTION
                    else
                        ErrorType.SERVICE_UNAVAILABLE
                    jokeCloudCallback.fail(errorType)
                }
            })
        }

    }
}