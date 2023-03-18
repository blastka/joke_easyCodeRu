package com.example.jokeappeasycoderu.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jokeappeasycoderu.State

interface Communication {
    fun showData(data: String)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)

    class Base: Communication {

        private val liveData = MutableLiveData<State>()

        override fun showData(data: String) {
            liveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
            liveData.observe(owner, observer)
        }

    }
}