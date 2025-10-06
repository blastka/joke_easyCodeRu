package com.example.jokeappeasycoderu.presentation

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.jokeappeasycoderu.JokeApp
import com.example.jokeappeasycoderu.R
import com.example.jokeappeasycoderu.core.*
import com.example.jokeappeasycoderu.presentation.CorrectButton
import com.example.jokeappeasycoderu.presentation.CorrectImageButton
import com.example.jokeappeasycoderu.presentation.CorrectProgress
import com.example.jokeappeasycoderu.presentation.CorrectTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as JokeApp).viewModel
        val button = findViewById<CorrectButton>(R.id.actionButton)
        val progressBar = findViewById<CorrectProgress>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val changeButton =
            findViewById<CorrectImageButton>(R.id.favoriteImageButton)
        progressBar.visibility = View.INVISIBLE
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }
        button.setOnClickListener {
            viewModel.getJoke()
        }
        viewModel.observe(this) { state ->
            state.show(progressBar, button, textView, changeButton)
        }
    }
}