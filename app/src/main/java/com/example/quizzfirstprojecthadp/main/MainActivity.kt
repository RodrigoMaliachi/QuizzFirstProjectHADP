package com.example.quizzfirstprojecthadp.main

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.Information
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.game.GameActivity
import com.example.quizzfirstprojecthadp.options.OptionsActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val info = Information()
    }

    private lateinit var playButton: Button
    private lateinit var optionsButton: ImageButton
    private lateinit var scoreButton: Button
    private lateinit var profileLayout: LinearLayout
    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppDatabase.getInstance(this.applicationContext).playerDao
        val factory = MainViewModelFactory(database)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        playButton = findViewById(R.id.playButton)
        optionsButton = findViewById(R.id.optionsButton)
        scoreButton = findViewById(R.id.scoreButton)
        profileLayout = findViewById(R.id.profileLayout)
        profileImage = findViewById(R.id.profileImage)
        userName = findViewById(R.id.userName)

        profileImage.setImageResource(viewModel.imageResource)
        userName.text = viewModel.player.name

        playButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivityForResult(intent, 1)
        }

        optionsButton.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }

        scoreButton.setOnClickListener {
//            val intent = Intent(this, ScoreActivity::class.java)
//            startActivity(intent)
        }

        profileLayout.setOnClickListener {
//            val intent = Intent(this, ProfileSettings::class.java)
//            startActivity(intent)
        }
    }
}