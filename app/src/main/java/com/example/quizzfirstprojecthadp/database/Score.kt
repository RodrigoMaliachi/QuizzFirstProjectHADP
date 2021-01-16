package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "score_id")
    var scoreId: Int = 0,

    @ForeignKey(entity = Player::class, parentColumns = ["player_id"], childColumns = ["player_id"])
    @ColumnInfo(name = "player_id")
    var playerId: Int = 0,

    @ColumnInfo(name = "score")
    var score: Int = 0,

    @ColumnInfo(name = "hints_was_used")
    var hintsWasUsed: Boolean = false,

    @ColumnInfo(name = "date")
    var date: Long = 0
)