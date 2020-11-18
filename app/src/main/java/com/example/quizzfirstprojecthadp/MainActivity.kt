package com.example.quizzfirstprojecthadp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var playButton: Button
    private lateinit var optionsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.playButton)
        optionsButton = findViewById(R.id.optionsButton)

        playButton.setOnClickListener {

        }

        optionsButton.setOnClickListener {

        }
    }
}