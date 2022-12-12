package com.example.jokeappeasycoderu

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)

    class Base(private val service: JokeService) : CloudDataSource {
        override fun getJoke(callback: JokeCloudCallback) {
            service.getJoke().enqueue(object : retrofit2.Callback<ServerModel.JokeServerModel> {
                override fun onResponse(
                    call: Call<ServerModel.JokeServerModel>,
                    response: Response<ServerModel.JokeServerModel>
                ) {
                    if (response.isSuccessful)
                        callback.provide(response.body()!!)
                    else
                        callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }

                override fun onFailure(call: Call<ServerModel.JokeServerModel>, t: Throwable) {
                    if (t is UnknownHostException)
                        callback.fail(ErrorType.NO_CONNECTION)
                    else
                        callback.fail(ErrorType.SERVICE_UNAVAILABLE)
                }
            })
        }

    }
}