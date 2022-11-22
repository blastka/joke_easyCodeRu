package com.example.jokeappeasycoderu

interface Model<S,E> {
    fun getJoke()
    fun init(callback: ResultCallback<S, E>)
    fun clear()
}

interface ResultCallback<S, E>{
    fun success(data:S)
    fun error(error: E)
}