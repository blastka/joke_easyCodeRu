package com.example.jokeappeasycoderu

interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(callback: JokeCallback)
    fun chooseDataSource(cached: Boolean)
}

interface ResultCallback {
    fun provideJoke(joke: Joke)
}