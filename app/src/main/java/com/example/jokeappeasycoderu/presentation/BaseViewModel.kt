package com.example.jokeappeasycoderu.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeappeasycoderu.data.Communication
import com.example.jokeappeasycoderu.domain.JokeInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun getJoke() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        interactor.getJoke().to().show(communication)
    }

    fun changeJokeStatus() = viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL))
            interactor.changeFavorites().to().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) =
        interactor.getFavoriteJokes(favorites)

    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)
}