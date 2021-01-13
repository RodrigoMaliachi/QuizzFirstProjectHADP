package com.example.quizzfirstprojecthadp.database

import androidx.room.*

@Dao
interface ScoreDao {

    @Insert
    fun insert(score: Score)

    @Update
    fun update(score: Score)

    @Delete
    fun delete(score: Score)

    @Query("SELECT * FROM scores ORDER BY score DESC")
    fun getScores(): List<Score>

    @Query("SELECT * FROM scores WHERE player_id = :id")
    fun getPlayerScores(id: Int): List<Score>

    @Delete
    fun delete(scores: List<Score>)
}