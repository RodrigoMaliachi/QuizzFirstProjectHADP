package com.example.quizzfirstprojecthadp.profile

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Player

class ProfileViewModel(private val database: AppDatabase): ViewModel() {

    private val activePlayer: Player
        get() = database.playerDao.getActivePlayer()

    val activePlayerImage: Int
        get() = activePlayer.getImageResource()

    val activePlayerName: String
        get() = activePlayer.name

    val activePlayerId: Int
        get() = activePlayer.playerId

    val isJustOnePlayer: Boolean
        get() = inactivePlayers.isEmpty()

    val inactivePlayers: List<Player>
        get() = database.playerDao.getInactivePlayers()

    fun deleteUser(userId: Int) {
        database.scoreDao.deletePlayerScores(userId)
        database.gameDao.deletePlayerGame(userId)
        database.questionSavedDao.deletePlayerQuestions(userId)
        database.settingsDao.deletePlayerSettings(userId)
        database.playerDao.deletePlayer(userId)
    }

    fun changeActivePlayer(userId: Int) {
        database.playerDao.updateActivePlayerToInactive()
        database.playerDao.updateInactivePlayerToActive(userId)
    }
}