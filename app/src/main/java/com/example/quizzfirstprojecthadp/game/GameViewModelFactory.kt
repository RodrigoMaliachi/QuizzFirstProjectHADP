package com.example.quizzfirstprojecthadp.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.AppDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(val database: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(database) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}