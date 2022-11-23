package com.example.jokeappeasycoderu

import android.app.Application

class JokeApp : Application() {

    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(TestViewModel(ResourceManager.Base(this)))
    }


}
