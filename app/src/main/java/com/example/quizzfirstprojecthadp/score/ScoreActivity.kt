package com.example.quizzfirstprojecthadp.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.game.GameActivity.Companion.SCORE

class ScoreActivity : AppCompatActivity() {

    private lateinit var finalScore: TextView
    private lateinit var scoreRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = "Tu puntuación: ${intent.getIntExtra(SCORE, 0)}"
        val database = AppDatabase.getInstance(this)
        val bestScores = database.scoreDao.getTop5()
        val names = mutableListOf<String>()
        bestScores.forEach {
            names.add(database.playerDao.getPlayerById(it.playerId).name)
        }
        val adapter = ScoreAdapter(names.toList(), bestScores)

        finalScore = findViewById(R.id.finalScore)
        finalScore.text = score
        scoreRV = findViewById(R.id.bestScoresRV)
        scoreRV.adapter = adapter
    }
}