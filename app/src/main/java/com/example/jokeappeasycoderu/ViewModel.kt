package com.example.jokeappeasycoderu

class ViewModel(private val model: Model<Joke, JokeFailure>) {
    private var callback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.callback = callback
        model.init(object : ResultCallback {
            override fun provideJoke(joke: Joke) {
                callback?.run {
                    callback.let {
                        joke.map(it)
                    }
                }
            }
        })
    }
    fun getJoke(){
        model.getJoke()
    }

   fun clear(){
       callback = null
       model.clear()
   }

    fun chooseFavorites(checked : Boolean){

    }

}

interface TextCallback{
    fun provideText(text: String)
}