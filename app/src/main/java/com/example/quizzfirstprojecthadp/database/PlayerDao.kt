package com.example.quizzfirstprojecthadp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player: Player)

    @Query("SELECT * FROM players WHERE player_id = :id")
    fun getPlayerById(id: Int): Player

    @Query("SELECT * FROM players WHERE active = 1")
    fun getActivePlayer(): Player

    @Query("SELECT * FROM players WHERE active = 1")
    fun getActivePlayerForUpdate(): Player

    @Query("SELECT * FROM players WHERE active = 0")
    fun getInactivePlayer(): LiveData<List<Player>>

    @Query("SELECT name FROM players")
    fun getListOfNames(): MutableList<String>

    @Query("SELECT name FROM players WHERE player_id = :id")
    fun getNameById(id: Int): String

    @Query("SELECT profile_image FROM players WHERE player_id = :id")
    fun getImageById(id: Int): Int
}