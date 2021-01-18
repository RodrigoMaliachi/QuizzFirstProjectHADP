package com.example.quizzfirstprojecthadp.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Player
import com.example.quizzfirstprojecthadp.game.GameActivity
import com.example.quizzfirstprojecthadp.options.OptionsActivity
import com.example.quizzfirstprojecthadp.profile.ProfileActivity
import com.example.quizzfirstprojecthadp.statistics.StatisticsActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PLAYER_ID = "player_id"
    }

    private lateinit var playButton: Button
    private lateinit var scoreButton: Button
    private lateinit var profileLayout: LinearLayout
    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView

    private lateinit var database: AppDatabase
    val player: Player
        get() = database.playerDao.getActivePlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this.applicationContext)

        playButton = findViewById(R.id.playButton)
        scoreButton = findViewById(R.id.scoreButton)
        profileLayout = findViewById(R.id.profileLayout)
        profileImage = findViewById(R.id.profileImage)
        userName = findViewById(R.id.name)

        profileImage.setImageResource(player.getImageResource())
        userName.text = player.name

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

        scoreButton.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }

        profileLayout.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.options -> {
                val intent = Intent(this, OptionsActivity::class.java)
                intent.putExtra(PLAYER_ID, player.playerId)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            profileImage.setImageResource(player.getImageResource())
            userName.text = player.name
        }
    }
}