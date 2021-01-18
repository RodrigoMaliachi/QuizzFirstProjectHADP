package com.example.quizzfirstprojecthadp.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.PlayerDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val database: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(database) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}