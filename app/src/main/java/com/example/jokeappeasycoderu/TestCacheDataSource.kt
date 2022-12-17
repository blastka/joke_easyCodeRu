package com.example.jokeappeasycoderu

class TestCacheDataSource: CacheDataSource {

    private val map = HashMap<Int, ServerModel.JokeServerModel>()
    override fun getJoke(jokeCacheCallback: JokeCacheCallback) {
        if (map.isEmpty()){
            jokeCacheCallback.fail()
        } else
            jokeCacheCallback.provide(map[0]!!)
    }

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