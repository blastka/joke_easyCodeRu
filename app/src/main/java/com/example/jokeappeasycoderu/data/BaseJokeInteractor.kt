package com.example.jokeappeasycoderu.data

import com.example.jokeappeasycoderu.domain.Joke
import com.example.jokeappeasycoderu.domain.JokeInteractor

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
) : JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Joke {
        return try {
            repository.changeJokeStatus().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override fun getFavoriteJokes(favorites: Boolean) =
        repository.chooseDataSource(favorites)
}