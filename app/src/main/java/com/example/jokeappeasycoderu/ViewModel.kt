package com.example.jokeappeasycoderu

class ViewModel(private val model: Model) {
    private var callback: DataCallback? = null

    private val jokeCallback = object : JokeCallback{
        override fun provide(joke: Joke) {
            callback?.let {
                joke.map(it)
            }
        }
    }

    fun init(callback: DataCallback) {
        this.callback = callback
        model.init(jokeCallback)
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

    fun changeJokeStatus(){
        model.changeJokeStatus(jokeCallback)
    }

}

interface TextCallback{
    fun provideText(text: String)
}