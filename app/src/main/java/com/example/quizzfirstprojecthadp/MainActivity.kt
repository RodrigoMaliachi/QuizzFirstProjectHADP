package com.example.quizzfirstprojecthadp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.quizzfirstprojecthadp.options.OptionsActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val info = Information()
    }

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
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }
    }
}