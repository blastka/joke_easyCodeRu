package com.example.jokeappeasycoderu

class TestCacheDataSource: CacheDataSource {

    private val list = ArrayList<Pair<Int, ServerModel.JokeServerModel>>()
    override fun getJoke(jokeCacheCallback: JokeCacheCallback) {
        if (list.isEmpty()){
            jokeCacheCallback.fail()
        } else
            jokeCacheCallback.provide(list.random().second)
    }

    override fun addOrRemove(id: Int, jokeServerModel: ServerModel.JokeServerModel): Joke {
        val found = list.find { it.first == id}
        return if (found != null){
            val joke = found.second.toJoke()
            list.remove(found)
            joke
        } else{
            list.add(Pair(id, jokeServerModel))
            jokeServerModel.toFavoriteJoke()
        }
    }
}