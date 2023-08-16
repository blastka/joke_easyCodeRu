package com.example.jokeappeasycoderu.data

import io.realm.Realm

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}