package com.example.jokeappeasycoderu.presentation

import android.content.Context
import android.util.AttributeSet
import com.example.jokeappeasycoderu.core.ShowImage

class CorrectImageButton :
    androidx.appcompat.widget.AppCompatImageButton, ShowImage {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //endregion
    override fun show(arg: Int) {
        setImageResource(arg)
    }
}