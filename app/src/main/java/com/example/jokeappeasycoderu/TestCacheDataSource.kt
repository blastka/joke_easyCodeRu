package com.example.jokeappeasycoderu

class TestCacheDataSource: CacheDataSource {

    private val map = HashMap<Int, ServerModel.JokeServerModel>()

    override fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke {
        return if (map.containsKey(id)){
            val joke = map[id]!!.toJoke()
            map.remove(id)
            joke
        } else{
            map[id] = jokeServerModel
            jokeServerModel.toFavoriteJoke()
        }
    }
}