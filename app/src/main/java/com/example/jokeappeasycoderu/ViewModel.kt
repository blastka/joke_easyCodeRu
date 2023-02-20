package com.example.jokeappeasycoderu

import android.util.Log

class ViewModel(private val model: Model<Joke, JokeFailure>) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback<Joke, JokeFailure> {
            override fun success(data: Joke) {
                callback.provideText(data.getJokeUi())
            }

            override fun error(error: JokeFailure) {
                callback.provideText(error.getMessage())
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

}

interface TextCallback{
    fun provideText(text: String)
}