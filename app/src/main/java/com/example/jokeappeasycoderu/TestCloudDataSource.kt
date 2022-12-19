package com.example.jokeappeasycoderu

class TestCloudDataSource(): CloudDataSource {
    private var count = 0

    override fun getJoke(callback: JokeCloudCallback) {
        val joke = Joke.Base(count, "testType", "testText$count", "testPunchLine$count")
        callback.provide(joke)
        count++
    }
}