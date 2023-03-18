package com.example.jokeappeasycoderu.data

import com.example.jokeappeasycoderu.*
import java.net.UnknownHostException

interface CloudDataSource: JokeDataFetcher<JokeServerModel, ErrorType> {
    override suspend fun getJoke(): Result<JokeServerModel, ErrorType>

    class Base(private val service: JokeService) : CloudDataSource {

        override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
            return try {
                val result = service.getJoke()
                Result.Success(result)
            } catch (e: Exception){
                val errorType = if (e is UnknownHostException)
                    ErrorType.NO_CONNECTION
                else
                    ErrorType.SERVICE_UNAVAILABLE
                Result.Error(errorType)
            }
        }
    }
}