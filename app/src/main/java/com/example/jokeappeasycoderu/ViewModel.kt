package com.example.jokeappeasycoderu

class ViewModel(private val model: Model<Any, Any>) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback<Any, Any> {
            override fun success(data: Any) {
                callback.provideText("success")
            }

            override fun error(error: Any) {
                callback.provideText("error")
            }

        })
    }
    fun getJoke(){
        model.getJoke()
    }

   fun clear(){
       callback = null
       model.clear()
   }

}

interface TextCallback{
    fun provideText(text: String)
}