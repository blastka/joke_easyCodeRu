package com.example.jokeappeasycoderu

class NoCachedJokes(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_cached_jokes)

}