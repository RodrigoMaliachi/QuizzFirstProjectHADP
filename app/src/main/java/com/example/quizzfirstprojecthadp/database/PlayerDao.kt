package com.example.quizzfirstprojecthadp.database

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

    @Query("SELECT * FROM players WHERE active = 0")
    fun getInactivePlayers(): List<Player>

    @Query("SELECT name FROM players")
    fun getListOfNames(): MutableList<String>

    @Query("UPDATE players SET active = 0 WHERE active = 1")
    fun updateActivePlayerToInactive()

    @Query("UPDATE players SET active = 1 WHERE player_id = :id")
    fun updateInactivePlayerToActive(id: Int)

    @Query("DELETE FROM players WHERE player_id = :id")
    fun deletePlayer(id: Int)

    @Query("SELECT * FROM players")
    fun getPlayers(): List<Player>
}