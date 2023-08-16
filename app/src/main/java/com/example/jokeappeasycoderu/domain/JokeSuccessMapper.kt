package com.example.jokeappeasycoderu.domain

import com.example.jokeappeasycoderu.data.JokeDataModelMapper

class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean) =
        Joke.Success(text, punchline, cached)
}