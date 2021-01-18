package com.example.quizzfirstprojecthadp.statistics

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Player
import com.example.quizzfirstprojecthadp.database.Score

class StatisticsViewModel(private val database: AppDatabase): ViewModel() {

    val statistics: List<Statistics>

    var index: Int = 0

    val player: Player
        get() = statistics[index].player

    val scores: List<Score>
        get() = statistics[index].scores

    init {
        val players = database.playerDao.getPlayers()
        val list = mutableListOf<Statistics>()
        players.forEachIndexed { i, it ->
            list.add(Statistics(it, database.scoreDao.getPlayerScores(it.playerId)))
            if (it.activePlayer) {
                index = i
            }
        }
        statistics = list.toList()
    }

    class Statistics(val player: Player, val scores: List<Score>) {
        override fun equals(other: Any?): Boolean {
            return player == (other as Statistics).player && scores == other.scores
        }

        override fun hashCode(): Int {
            var result = player.hashCode()
            result = 31 * result + scores.hashCode()
            return result
        }
    }
}
