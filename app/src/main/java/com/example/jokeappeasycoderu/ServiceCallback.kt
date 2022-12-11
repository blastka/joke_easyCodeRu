package com.example.jokeappeasycoderu

interface ServiceCallback {
    fun returnSuccess(data: String)
    fun returnError(type: ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    OTHER
}