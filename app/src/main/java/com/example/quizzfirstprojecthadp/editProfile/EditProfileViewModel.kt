package com.example.quizzfirstprojecthadp.editProfile

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Player
import com.example.quizzfirstprojecthadp.database.Settings

class EditProfileViewModel(private val database: AppDatabase, private val userId: Int) : ViewModel() {

    private val player: Player = if (userId == -1) {
        Player(name = "", profileImage = 1, activePlayer = true)
    } else {
        database.playerDao.getPlayerById(userId)
    }

    private val names = database.playerDao.getListOfNames()

    var imageResource: Int
        get() = player.getImageResource()
        set(value) { player.profileImage = value}

    private val originalImage = player.profileImage
    private val originalName = player.name

    val name: String
        get() = player.name

    val isActive: Boolean
        get() = player.activePlayer

    init {
        if (userId != -1) {
            names.remove(name)
        }
    }

    fun onPositiveClick(name: String): Int {
        if (name == "")
            return -1
        names.forEach { otherName ->
            if (name == otherName)
                return 0
        }
        if (userId != -1) {
            if (name != originalName || originalImage != player.profileImage) {
                player.name = name
                database.playerDao.update(player)
                return 1
            }
            return 2
        }
        database.playerDao.updateActivePlayerToInactive()
        player.name = name
        database.playerDao.insert(player)
        val newPlayerId = database.playerDao.getActivePlayer().playerId
        database.settingsDao.insert(Settings(playerId = newPlayerId))
        return 3
    }
}