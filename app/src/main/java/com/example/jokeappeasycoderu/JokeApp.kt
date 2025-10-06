package com.example.jokeappeasycoderu

import android.app.Application
import com.example.jokeappeasycoderu.core.ResourceManager
import com.example.jokeappeasycoderu.data.*
import com.example.jokeappeasycoderu.domain.JokeSuccessMapper
import com.example.jokeappeasycoderu.presentation.BaseViewModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: BaseViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val cacheDataSource = BaseCachedDataSource(
            BaseRealmProvider(),
            JokeRealmMapper()
        )
        val resourceManager = ResourceManager.Base(this)
        val cloudDataSource =
            BaseCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseJokeRepository(
            cacheDataSource, cloudDataSource,
            BaseCachedJoke()
        )
        val interactor = BaseJokeInteractor(
            repository,
            JokeFailureFactory(resourceManager), JokeSuccessMapper()
        )
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}