package com.example.jokeappeasycoderu

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) :
    Model {
    private var jokeCallback: JokeCallback? = null

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJokes by lazy{NoCachedJokes(resourceManager)}
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override fun getJoke() {
        if(getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCacheCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    jokeCallback?.provide(JokeUiModel.Failed(noCachedJokes.getMessage()))
                }

            })
        } else
            cloudDataSource.getJoke(object: JokeCloudCallback{
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toJoke())
                }

                override fun fail(errorType: ErrorType) {
                    cachedJoke = null
                    val failure = if (errorType == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(JokeUiModel.Failed(failure.getMessage()))
                }
            })
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}