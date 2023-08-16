package com.example.jokeappeasycoderu.data

import com.example.jokeappeasycoderu.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealmModel : RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchLine: String = ""
    override fun to() = JokeDataModel(id, text, punchLine, true)
}