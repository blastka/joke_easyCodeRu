package com.example.jokeappeasycoderu

interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(callback: JokeCallback)
}

interface ResultCallback {
    fun provideJoke(joke: Joke)
}