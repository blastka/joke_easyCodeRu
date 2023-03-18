package com.example.jokeappeasycoderu.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeappeasycoderu.Model
import com.example.jokeappeasycoderu.State
import kotlinx.coroutines.launch

class JokeViewModel(private val model: Model, private val communication: Communication): ViewModel() {

    fun getJoke(){
        viewModelScope.launch {
            communication.showData(model.getJoke().getJokeUi())
        }
    }

    fun chooseFavorites(favorites : Boolean){
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus(){
        viewModelScope.launch {
            model.changeJokeStatus()?.let {
                communication.showData(it.getJokeUi())
            }

        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }

}
