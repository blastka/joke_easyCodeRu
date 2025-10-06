package com.example.jokeappeasycoderu.data

import androidx.annotation.StringRes
import com.example.jokeappeasycoderu.R
import com.example.jokeappeasycoderu.core.ResourceManager

abstract class BaseJokeFailure(
    private val resourceManager:
    ResourceManager
) : JokeFailure {
    @StringRes
    protected abstract fun getMessageResId(): Int
    override fun getMessage(): String =
        resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager) :
    BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager) :
    BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_unavailable
}

class NoCachedJokes(resourceManager: ResourceManager) :
    BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_jokes
}

class GenericError(resourceManager: ResourceManager) :
    BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.generic_fail_message
}