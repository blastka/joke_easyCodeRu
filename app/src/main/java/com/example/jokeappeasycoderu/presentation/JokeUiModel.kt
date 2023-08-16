package com.example.jokeappeasycoderu.core

import androidx.annotation.DrawableRes
import com.example.jokeappeasycoderu.R
import com.example.jokeappeasycoderu.data.Communication
import com.example.jokeappeasycoderu.presentation.State

abstract class JokeUiModel(
    private val text: String, private val punchline:
    String
) {
    protected open fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) =
        communication.showState(
            State.Initial(text(), getIconResId())
        )
}

class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(
    text,
    punchline
) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String, punchline: String) : JokeUiModel(
    text,
    punchline
) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) =
        communication.showState(
            State.Failed(text(), getIconResId())
        )
}