package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    var questionId: Int = 0,

    @ColumnInfo(name = "question")
    var question: String = "",

    @ColumnInfo(name = "category")
    var category: Int = 0,

    @ColumnInfo(name = "option_1")
    var option1: String = "",

    @ColumnInfo(name = "option_2")
    var option2: String = "",

    @ColumnInfo(name = "option_3")
    var option3: String = "",

    @ColumnInfo(name = "option_4")
    var option4: String = ""
)