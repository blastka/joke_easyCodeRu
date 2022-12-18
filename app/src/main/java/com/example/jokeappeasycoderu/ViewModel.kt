package com.example.jokeappeasycoderu

class ViewModel(private val model: Model) {
    private var dataCallback: DataCallback? = null

    private val jokeCallback = object : JokeCallback{
        override fun provide(joke: Joke) {
            dataCallback?.let {
                joke.map(it)
            }
        }
    }

    fun init(callback: DataCallback) {
        this.dataCallback = callback
        model.init(jokeCallback)
    }

    fun getJoke(){
        model.getJoke()
    }

   fun clear(){
       dataCallback = null
       model.clear()
   }

    fun chooseFavorites(favorites : Boolean){
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus(){
        model.changeJokeStatus(jokeCallback)
    }

}

interface TextCallback{
    fun provideText(text: String)
}