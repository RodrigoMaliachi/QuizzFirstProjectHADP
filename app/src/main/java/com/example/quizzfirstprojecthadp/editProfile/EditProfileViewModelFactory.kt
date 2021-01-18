package com.example.quizzfirstprojecthadp.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.database.AppDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class EditProfileViewModelFactory(private val database: AppDatabase, private val userId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java))
            return EditProfileViewModel(database, userId) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}