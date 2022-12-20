package com.example.jokeappeasycoderu

interface Joke {
    fun toJoke(): JokeUiModel
    fun toFavoriteJoke(): JokeUiModel.FavoriteJoke
    suspend fun change(cacheDataSource: CacheDataSource): JokeUiModel
    fun toJokeRealm(): JokeRealm

    class Base(
        private val id: Int,
        private val type: String,
        private val text: String,
        private val punchline: String
    ) : Joke {

        override fun toJoke(): JokeUiModel = JokeUiModel.Base(text, punchline)

        override fun toFavoriteJoke(): JokeUiModel.FavoriteJoke =
            JokeUiModel.FavoriteJoke(text, punchline)

        override suspend fun change(cacheDataSource: CacheDataSource): JokeUiModel =
            cacheDataSource.addOrRemove(id, this)

        override fun toJokeRealm(): JokeRealm
            = JokeRealm().also {
                it.id = id
                it.type = type
                it.text = text
                it.punchLine = punchline

        }

    }
}