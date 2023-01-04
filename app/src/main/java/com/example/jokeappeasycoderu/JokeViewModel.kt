package com.example.jokeappeasycoderu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JokeViewModel(private val model: Model): ViewModel() {
    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.dataCallback = callback
    }

    fun getJoke(){
        viewModelScope.launch {
            val uiModel = model.getJoke()
            dataCallback?.let {
                uiModel.map(it)
            }
        }
    }

   fun clear(){
       dataCallback = null
   }

    fun chooseFavorites(favorites : Boolean){
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus(){
        viewModelScope.launch {
            val uiModel = model.changeJokeStatus()
            dataCallback?.let {
                uiModel?.map(it)
            }
        }
    }

}

interface TextCallback{
    fun provideText(text: String)
}