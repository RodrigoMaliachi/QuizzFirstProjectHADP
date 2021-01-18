package com.example.quizzfirstprojecthadp.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.AppDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class StatisticsViewModelFactory(private val database: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatisticsViewModel::class.java))
            return StatisticsViewModel(database) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}