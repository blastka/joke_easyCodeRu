package com.example.jokeappeasycoderu

class TestViewModel(private val resourceManager: ResourceManager) : Model<Joke, JokeFailure> {

    private var callback: ResultCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideJoke(Joke.Base("testText", "testPunchline"))
                1 -> callback?.provideJoke(Joke.FavoriteJoke("favorite", "favorite"))
                2 -> callback?.provideJoke(Joke.Failed(serviceUnavailable.getMessage()))
            }
            count++
            if (count == 3)
                count = 0
        }.start()
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}