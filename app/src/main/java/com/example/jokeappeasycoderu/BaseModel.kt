package com.example.jokeappeasycoderu

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(private val service: JokeService, private val resourceManager: ResourceManager) :
    Model<Joke, JokeFailure> {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
       /* service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO> {
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if (response.isSuccessful){
                    callback?.success(response.body()!!.toJoke())
                }else{
                    callback?.error(serviceUnavailable)
                }
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if (t is UnknownHostException)
                    callback?.error(noConnection)
                else
                    callback?.error(serviceUnavailable)
            }
        })*/
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}