package com.example.jokeappeasycoderu

class BaseModel(private val service: JokeService, private val resourceManager: ResourceManager) :
    Model<Joke, JokeFailure> {
    private var callback: ResultCallback<Joke, JokeFailure>? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
        service.getJoke(object : ServiceCallback {

            override fun returnSuccess(data: JokeDTO) {
                callback?.success(data.toJoke())
            }

            override fun returnError(type: ErrorType) {
                when (type) {
                    ErrorType.NO_CONNECTION -> callback?.error(noConnection)
                    ErrorType.OTHER -> callback?.error(serviceUnavailable)
                }
            }

        })
    }

    override fun init(callback: ResultCallback<Joke, JokeFailure>) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}