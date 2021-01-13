package com.example.quizzfirstprojecthadp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.PlayerDao

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val database: PlayerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(database) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}