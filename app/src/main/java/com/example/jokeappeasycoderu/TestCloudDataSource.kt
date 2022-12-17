package com.example.jokeappeasycoderu

class TestCloudDataSource(): CloudDataSource {
    private var count = 0

    override fun getJoke(callback: JokeCloudCallback) {
        val joke = ServerModel.JokeServerModel(count, "testType", "testText", "testPunchLine")
        callback.provide(joke)
        count++
    }
}