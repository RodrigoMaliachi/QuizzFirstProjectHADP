package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizzfirstprojecthadp.R

@Entity(tableName = "players")
data class Player(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "player_id")
        var playerId: Int = 0,

        @ColumnInfo(name = "name")
        var name: String = "",

        @ColumnInfo(name = "profile_image")
        var profileImage: Int = 0,

        @ColumnInfo(name = "active", typeAffinity = ColumnInfo.INTEGER)
        var activePlayer: Boolean = false
) {
    fun getImageResource() = when (profileImage) {
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