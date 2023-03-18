package com.example.jokeappeasycoderu.data

import com.example.jokeappeasycoderu.*

class Repository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) :
    Model {

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJokes by lazy { NoCachedJokes(resourceManager) }
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override suspend fun getJoke(): JokeUiModel {
        if (getJokeFromCache) {
            return when (val result = cacheDataSource.getJoke()) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    JokeUiModel.Failed(noCachedJokes.getMessage())
                }
            }
        } else {
            return when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJokeDataModel().let {
                        cachedJoke = it
                        it.toJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    val failure = if (result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else serviceUnavailable
                    JokeUiModel.Failed(failure.getMessage())
                }
            }
        }
    }

    override suspend fun changeJokeStatus(): JokeUiModel? {
        return cachedJoke?.change(cacheDataSource)
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    private abstract inner class BaseResultHandler<S, E>(private val jokeDataFetcher: JokeDataFetcher<S, E>) :
        ResultHandler<S, E> {
        suspend fun process(): JokeUiModel {
            return handlerResult(jokeDataFetcher.getJoke())
        }
    }
}

