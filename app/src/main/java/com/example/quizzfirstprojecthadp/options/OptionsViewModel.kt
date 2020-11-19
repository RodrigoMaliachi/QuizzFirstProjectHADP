package com.example.quizzfirstprojecthadp.options

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.MainActivity
import com.example.quizzfirstprojecthadp.R

class OptionsViewModel : ViewModel() {

    var isEverythingChecked: Boolean

    var isAtLeastOneChecked: Boolean

    init {
        MainActivity.info.apply {
            isAtLeastOneChecked = anime || cine || furry || musica || toons || videojuegos
            isEverythingChecked = anime && cine && furry && musica && toons && videojuegos
        }
    }

    fun initializeBoxes(boxes: List<CheckBox>) {
        MainActivity.info.apply {
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
        MainActivity.info.apply {
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
}