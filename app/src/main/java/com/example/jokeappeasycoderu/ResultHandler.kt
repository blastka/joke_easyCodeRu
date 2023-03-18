package com.example.jokeappeasycoderu

interface ResultHandler<S, E> {
    fun handlerResult(resul: Result<S,E>): JokeUiModel
}