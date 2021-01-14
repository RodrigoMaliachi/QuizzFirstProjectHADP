package com.example.quizzfirstprojecthadp.options

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.Settings
import com.example.quizzfirstprojecthadp.database.SettingsDao

class OptionsViewModel(val database: SettingsDao, val playerId: Int) : ViewModel() {

    val settings: Settings = database.getSettingsFromPlayer(playerId)

    var isEverythingChecked: Boolean

    var isAtLeastOneChecked: Boolean

    var questionsQuantity: Int
        get() = settings.questionsQuantity
        set(value) { settings.questionsQuantity = value }

    var hintsQuantity: Int
        get() = settings.hintsQuantity
        set(value) { settings.hintsQuantity = value }

    var difficulty: Int
        get() = settings.difficulty
        set(value) { settings.difficulty = value }

    var isHintsEnabled: Boolean
        get() = settings.hintsEnabled
        set(value) { settings.hintsEnabled = value }

    init {
        settings.apply {
            isAtLeastOneChecked = anime || cine || furry || deportes || toons || videojuegos
            isEverythingChecked = anime && cine && furry && deportes && toons && videojuegos
        }
    }

    fun initializeBoxes(boxes: List<CheckBox>) {
        settings.apply {
            for (box in boxes) {
                when (box.id) {
                    R.id.animeCheckBox -> box.isChecked = anime
                    R.id.cineCheckBox -> box.isChecked = cine
                    R.id.furryCheckBox -> box.isChecked = furry
                    R.id.deportesCheckBox -> box.isChecked = deportes
                    R.id.toonsCheckBox -> box.isChecked = toons
                    R.id.videojuegosCheckBox -> box.isChecked = videojuegos
                    R.id.todosCheckBox -> box.visibility = if (isEverythingChecked) View.INVISIBLE else View.VISIBLE
                }
            }
        }
    }

    fun changeTopics(id: Int, isChecked: Boolean) {
        settings.apply {
            when (id) {
                R.id.animeCheckBox -> anime = isChecked
                R.id.cineCheckBox -> cine = isChecked
                R.id.furryCheckBox -> furry = isChecked
                R.id.deportesCheckBox -> deportes = isChecked
                R.id.toonsCheckBox -> toons = isChecked
                R.id.videojuegosCheckBox -> videojuegos = isChecked
            }
            isAtLeastOneChecked = anime || cine || furry || deportes || toons || videojuegos
            isEverythingChecked = anime && cine && furry && deportes && toons && videojuegos
        }
    }

    //Función que devuelve el adaptador para un Spinner
    fun spinnerAdapter(context: Context, resource: Int) = ArrayAdapter
        .createFromResource(context, resource, android.R.layout.simple_spinner_item)
        .also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
}