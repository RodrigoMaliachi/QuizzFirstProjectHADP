package com.example.quizzfirstprojecthadp.options

import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.lifecycle.ViewModel

import com.example.quizzfirstprojecthadp.MainActivity.Companion.info
import com.example.quizzfirstprojecthadp.R

class OptionsViewModel : ViewModel() {

    var isEverythingChecked: Boolean

    var isAtLeastOneChecked: Boolean

    var questionsQuantity: Int
        get() = info.questionsQuantity
        set(value) { info.questionsQuantity = value }

    var hintsQuantity: Int
        get() = info.hintsQuantity
        set(value) { info.hintsQuantity = value }

    var difficulty: Int
        get() = info.difficulty
        set(value) { info.difficulty = value }

    var isHintsEnabled: Boolean
        get() = info.isHintsEnabled
        set(value) { info.isHintsEnabled = value }

    init {
        info.apply {
            isAtLeastOneChecked = anime || cine || furry || musica || toons || videojuegos
            isEverythingChecked = anime && cine && furry && musica && toons && videojuegos
        }
    }

    fun initializeBoxes(boxes: List<CheckBox>) {
        info.apply {
            for (box in boxes) {
                when (box.id) {
                    R.id.animeCheckBox -> box.isChecked = anime
                    R.id.cineCheckBox -> box.isChecked = cine
                    R.id.furryCheckBox -> box.isChecked = furry
                    R.id.musicaCheckBox -> box.isChecked = musica
                    R.id.toonsCheckBox -> box.isChecked = toons
                    R.id.videojuegosCheckBox -> box.isChecked = videojuegos
                    R.id.todosCheckBox -> box.visibility = if (isEverythingChecked) View.INVISIBLE else View.VISIBLE
                }
            }
        }
    }

    fun changeTopics(id: Int, isChecked: Boolean) {
        info.apply {
            when (id) {
                R.id.animeCheckBox -> anime = isChecked
                R.id.cineCheckBox -> cine = isChecked
                R.id.furryCheckBox -> furry = isChecked
                R.id.musicaCheckBox -> musica = isChecked
                R.id.toonsCheckBox -> toons = isChecked
                R.id.videojuegosCheckBox -> videojuegos = isChecked
            }
            isAtLeastOneChecked = anime || cine || furry || musica || toons || videojuegos
            isEverythingChecked = anime && cine && furry && musica && toons && videojuegos
        }
    }

    //Función que devuelve el adaptador para un Spinner
    fun spinnerAdapter(context: Context, resource: Int) = ArrayAdapter
        .createFromResource(context, resource, android.R.layout.simple_spinner_item)
        .also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
}