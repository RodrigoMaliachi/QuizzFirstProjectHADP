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

    @ColumnInfo(name = "art", typeAffinity = ColumnInfo.INTEGER)
    var art: Boolean = true,

    @ColumnInfo(name = "culture", typeAffinity = ColumnInfo.INTEGER)
    var culture: Boolean = true,

    @ColumnInfo(name = "geography", typeAffinity = ColumnInfo.INTEGER)
    var geography: Boolean = true,

    @ColumnInfo(name = "history", typeAffinity = ColumnInfo.INTEGER)
    var history: Boolean = true,

    @ColumnInfo(name = "music", typeAffinity = ColumnInfo.INTEGER)
    var music: Boolean = true,

    @ColumnInfo(name = "video_games", typeAffinity = ColumnInfo.INTEGER)
    var videoGames: Boolean = true,

    @ColumnInfo(name = "difficulty")
    var difficulty: Int = 2,

    @ColumnInfo(name = "questions_quantity")
    var questionsQuantity: Int = 10,

    @ColumnInfo(name = "hints_enabled", typeAffinity = ColumnInfo.INTEGER)
    var hintsEnabled: Boolean = false,

    @ColumnInfo(name = "clues_quantity")
    var hintsQuantity: Int = 1
)