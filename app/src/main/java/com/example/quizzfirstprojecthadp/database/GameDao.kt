package com.example.quizzfirstprojecthadp.database

import androidx.room.*

@Dao
interface GameDao {

    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Delete
    fun delete(game: Game)

    @Query("SELECT * FROM games WHERE player_id = :id")
    fun getGame(id: Int): Game?

    @Query("DELETE FROM games WHERE player_id = :id")
    fun deletePlayerGame(id: Int)
}