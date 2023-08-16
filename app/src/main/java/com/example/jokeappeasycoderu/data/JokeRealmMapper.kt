package com.example.jokeappeasycoderu.data

class JokeRealmMapper : JokeDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean) =
        JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = text
            joke.punchLine = punchline
        }
}