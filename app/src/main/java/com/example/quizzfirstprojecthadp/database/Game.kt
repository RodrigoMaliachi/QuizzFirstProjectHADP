package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.quizzfirstprojecthadp.database.Question

@Entity(tableName = "games")
data class Game(
        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_id")
    var gameId: Int = 0,

        @ForeignKey(entity = Player::class, parentColumns = ["player_id"], childColumns = ["player_id"])
    @ColumnInfo(name = "player_id")
    var playerId: Int = 0,

        @ColumnInfo(name = "finished")
    var isFinished: Boolean = true,

        @ColumnInfo(name = "question_index")
    var questionIndex: Int = 0,

        @ColumnInfo(name = "hints")
    var hints: Int = 0,

        @ColumnInfo(name = "hints_used")
    var hintsUsed: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_1"])
    @ColumnInfo(name = "question_1")
    var question1: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_2"])
    @ColumnInfo(name = "question_2")
    var question2: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_3"])
    @ColumnInfo(name = "question_3")
    var question3: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_4"])
    @ColumnInfo(name = "question_4")
    var question4: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_5"])
    @ColumnInfo(name = "question_5")
    var question5: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_6"])
    @ColumnInfo(name = "question_6")
    var question6: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_7"])
    @ColumnInfo(name = "question_7")
    var question7: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_8"])
    @ColumnInfo(name = "question_8")
    var question8: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_9"])
    @ColumnInfo(name = "question_9")
    var question9: Int = 0,

        @ForeignKey(entity = Question::class, parentColumns = ["question_saved_id"], childColumns = ["question_10"])
    @ColumnInfo(name = "question_10")
    var question10: Int = 0
)