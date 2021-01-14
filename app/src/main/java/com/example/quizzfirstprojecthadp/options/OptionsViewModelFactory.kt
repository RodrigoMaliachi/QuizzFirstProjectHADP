package com.example.quizzfirstprojecthadp.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.SettingsDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class OptionsViewModelFactory(val database: SettingsDao, val playerId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OptionsViewModel::class.java))
            return OptionsViewModel(database, playerId) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}