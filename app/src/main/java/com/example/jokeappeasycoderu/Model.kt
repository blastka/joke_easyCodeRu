package com.example.jokeappeasycoderu

interface Model {
    suspend fun getJoke(): JokeUiModel
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(callback: JokeCallback)
    fun chooseDataSource(cached: Boolean)
}

interface ResultCallback {
    fun provideJoke(joke: JokeUiModel)
}