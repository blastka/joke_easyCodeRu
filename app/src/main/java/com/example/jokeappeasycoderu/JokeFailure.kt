package com.example.jokeappeasycoderu

interface JokeFailure {
    fun getMessage(): String
}

/**
 * Два класса для ошибки
 */

class NoConnection(private val resourceManager: ResourceManager): JokeFailure{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.no_connection)
    }

}

class ServiceUnavailable(private val resourceManager: ResourceManager): JokeFailure{
    override fun getMessage(): String {
        return resourceManager.getString(R.string.service_unavailable)
    }

}