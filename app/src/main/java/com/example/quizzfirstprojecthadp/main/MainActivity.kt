package com.example.quizzfirstprojecthadp.main

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.game.GameActivity
import com.example.quizzfirstprojecthadp.options.OptionsActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PLAYER_ID = "player_id"
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

        val database = AppDatabase.getInstance(this.applicationContext)
        val factory = MainViewModelFactory(database.playerDao)
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

            val playerId = database.playerDao.getActivePlayer().playerId
            val game = database.gameDao.getGame(playerId)

            game?.let {
                if (!it.isFinished) {
                    this.let {
                        val builder = AlertDialog.Builder(it)
                        builder.setMessage(R.string.new_game_message)
                        builder.apply {
                            setPositiveButton(R.string.new_game_possitive_button) { _, _ ->
                                val intent = Intent(this@MainActivity, GameActivity::class.java)
                                startActivity(intent)
                            }
                            setNeutralButton(R.string.new_game_neutral_button) { _, _ ->
                                game.isFinished = true
                                database.gameDao.update(game)
                                val intent = Intent(this@MainActivity, GameActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        builder.create()
                    }.show()
                } else {
                    val intent = Intent(this@MainActivity, GameActivity::class.java)
                    startActivity(intent)
                }
            }

            if (game == null) {
                val intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
            }
        }

        optionsButton.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            intent.putExtra(PLAYER_ID, viewModel.player.playerId)
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