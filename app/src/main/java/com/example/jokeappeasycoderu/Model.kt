package com.example.jokeappeasycoderu

interface Model<S,E> {
    fun getJoke()
    fun init(callback: ResultCallback)
    fun clear()
}

interface ResultCallback {
    fun provideJoke(joke: Joke)
}