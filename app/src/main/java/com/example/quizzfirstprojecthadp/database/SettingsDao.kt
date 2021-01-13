package com.example.quizzfirstprojecthadp.database

import androidx.room.*

@Dao
interface SettingsDao {

    @Insert
    fun insert(settings: Settings)

    @Update
    fun update(settings: Settings)

    @Delete
    fun delete(settings: Settings)

    @Query("SELECT * FROM settings WHERE settings_id = :id")
    fun getSettingsById(id: Int): Settings

    @Query("SELECT * FROM settings WHERE player_id = :id")
    fun getSettingsFromPlayer(id: Int): Settings
}