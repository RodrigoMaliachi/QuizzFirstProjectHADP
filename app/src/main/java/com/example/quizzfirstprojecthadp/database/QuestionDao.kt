package com.example.quizzfirstprojecthadp.database

import androidx.room.*

@Dao
interface QuestionDao {

    @Insert
    fun insert(question: Question)

    @Update
    fun update(question: Question)

    @Delete
    fun delete(question: Question)

    @Query("SELECT * FROM questions WHERE question_id = :id")
    fun getQuestion(id: Int): Question
}