package com.example.jokeappeasycoderu.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.jokeappeasycoderu.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.button)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val favoriteImageView = findViewById<ImageView>(R.id.favoriteImageView)
        checkBox.setOnCheckedChangeListener{_, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : DataCallback{
            override fun provideText(text: String) = runOnUiThread{
                button.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconRes(id: Int) = runOnUiThread{
                favoriteImageView.setImageResource(id)
            }

        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}