package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "settings_id")
    var playerSettingsId: Int = 0,

    @ForeignKey(entity = Player::class, parentColumns = ["player_id"], childColumns = ["player_id"])
    @ColumnInfo(name = "player_id")
    var playerId: Int = 0,

    @ColumnInfo(name = "anime", typeAffinity = ColumnInfo.INTEGER)
    var anime: Boolean = true,

    @ColumnInfo(name = "cine", typeAffinity = ColumnInfo.INTEGER)
    var cine: Boolean = true,

    @ColumnInfo(name = "furry", typeAffinity = ColumnInfo.INTEGER)
    var furry: Boolean = true,

    @ColumnInfo(name = "deportes", typeAffinity = ColumnInfo.INTEGER)
    var deportes: Boolean = true,

    @ColumnInfo(name = "toons", typeAffinity = ColumnInfo.INTEGER)
    var toons: Boolean = true,

    @ColumnInfo(name = "videojuegos", typeAffinity = ColumnInfo.INTEGER)
    var videojuegos: Boolean = true,

    @ColumnInfo(name = "difficulty")
    var difficulty: Int = 2,

    @ColumnInfo(name = "questions_quantity")
    var questionsQuantity: Int = 10,

    @ColumnInfo(name = "hints_enabled", typeAffinity = ColumnInfo.INTEGER)
    var hintsEnabled: Boolean = false,

    @ColumnInfo(name = "clues_quantity")
    var hintsQuantity: Int = 1
)