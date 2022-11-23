package com.example.jokeappeasycoderu

class TestViewModel: Model<Any, Any> {

    private var callback: ResultCallback<Any, Any>? = null
    private var count = 1

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            if (count % 2 == 0) {
                callback?.success("success")
            } else {
                callback?.error("error")
            }
            count++
        }.start()
    }

    override fun init(callback: ResultCallback<Any, Any>) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}