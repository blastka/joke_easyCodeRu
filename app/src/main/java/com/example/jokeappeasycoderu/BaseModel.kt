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
    private var cachedJokeServerModel: ServerModel? = null

    override fun getJoke() {
        cloudDataSource.getJoke(object : JokeCloudCallback{
            override fun provide(joke: ServerModel.JokeServerModel) {
                cachedJokeServerModel = joke
                jokeCallback?.provide(joke.toJoke())
            }

            override fun fail(error: ErrorType) {
                cachedJokeServerModel = null
                val failure = if (error == ErrorType.NO_CONNECTION) noConnection
                else
                    serviceUnavailable
                jokeCallback?.provide(Joke.Failed(failure.getMessage()))
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
        cachedJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }
}