package com.example.jokeappeasycoderu.data

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}