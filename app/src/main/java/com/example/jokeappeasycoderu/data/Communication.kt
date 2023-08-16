package com.example.jokeappeasycoderu.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.jokeappeasycoderu.presentation.State

interface Communication {
    fun showState(state: State)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int): Boolean
}