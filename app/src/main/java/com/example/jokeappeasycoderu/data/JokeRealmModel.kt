package com.example.jokeappeasycoderu.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel: RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchLine: String = ""
    var type: String = ""

    fun toJokeDataModel() = JokeDataModel(id, type, text, punchLine)
}