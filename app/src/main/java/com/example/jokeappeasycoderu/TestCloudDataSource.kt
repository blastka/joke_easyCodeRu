package com.example.jokeappeasycoderu

class TestCloudDataSource(): CloudDataSource {
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(ServerModel.JokeServerModel(0, "testType", "testText", "testPunchLine"))
    }
}