package com.example.jokeappeasycoderu.data

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedJokesException : IOException()