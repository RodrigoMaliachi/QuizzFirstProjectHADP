package com.example.quizzfirstprojecthadp.main

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.Player
import com.example.quizzfirstprojecthadp.database.PlayerDao

class MainViewModel(val database: PlayerDao): ViewModel() {

    private var _player: Player
    val player
        get() = _player

    private var _imageResource: Int
    val imageResource
        get() = _imageResource

    init {
        _player = database.getActivePlayer()
        _imageResource = when (player.profileImage) {
            1 -> R.drawable.avatar_01
            2 -> R.drawable.avatar_02
            3 -> R.drawable.avatar_03
            4 -> R.drawable.avatar_04
            5 -> R.drawable.avatar_05
            6 -> R.drawable.avatar_06
            7 -> R.drawable.avatar_07
            8 -> R.drawable.avatar_08
            9 -> R.drawable.avatar_09
            10 -> R.drawable.avatar_10
            11 -> R.drawable.avatar_11
            12 -> R.drawable.avatar_12
            13 -> R.drawable.avatar_13
            14 -> R.drawable.avatar_14
            15 -> R.drawable.avatar_15
            else -> R.drawable.avatar_16
        }
    }

    fun updateActivePlayer() { _player = database.getActivePlayer() }
}