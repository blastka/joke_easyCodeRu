package com.example.jokeappeasycoderu.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.jokeappeasycoderu.JokeApp
import com.example.jokeappeasycoderu.R
import com.example.jokeappeasycoderu.TextCallback
import com.example.jokeappeasycoderu.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.button)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        /**
         * Этот init передает в ВМ реализованый анонимный класс который колбэк
         * что при возврате в него кнопка станет активной, прогресс бар скроется
         * у текствью присвоится текст ,который отдали в колбэк
         * И так как
         */
        viewModel.init(object : TextCallback{
            override fun provideText(text: String) {
                button.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                textView.text = text

            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}