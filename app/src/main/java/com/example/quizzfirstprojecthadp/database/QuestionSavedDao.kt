package com.example.quizzfirstprojecthadp.database

import androidx.room.*

@Dao
interface QuestionSavedDao {

    @Insert
    fun insert(questionSaved: QuestionSaved)

    @Update
    fun update(questionSaved: QuestionSaved)

    @Delete
    fun delete(questionSaved: QuestionSaved)

    @Delete
    fun delete(questionSavedList: List<QuestionSaved>)

    @Query("SELECT * FROM questions_saved WHERE player_id = :playerId")
    fun getQuestionsFromPlayer(playerId: Int): List<QuestionSaved>

    @Query("SELECT * FROM questions_saved WHERE question_saved_id = :id")
    fun getQuestionSaved(id: Int): QuestionSaved

    @Query("DELETE FROM questions_saved WHERE player_id = :id")
    fun deletePlayerQuestions(id: Int)
}