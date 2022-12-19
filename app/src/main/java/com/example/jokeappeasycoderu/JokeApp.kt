package com.example.jokeappeasycoderu

import android.app.Application
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {

    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .build()
        Realm.setDefaultConfiguration(config)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(
            BaseModel(CacheDataSource.Base(Realm.getDefaultInstance()), CloudDataSource.Base(retrofit.create(JokeService::class.java)),
                ResourceManager.Base(this)
            )
        )
    }


}
