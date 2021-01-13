package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)