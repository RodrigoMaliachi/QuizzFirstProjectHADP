package com.example.quizzfirstprojecthadp.options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.R

class OptionsActivity : AppCompatActivity() {

    private lateinit var animeBox: CheckBox
    private lateinit var cineBox: CheckBox
    private lateinit var furryBox: CheckBox
    private lateinit var musicaBox: CheckBox
    private lateinit var toonsBox: CheckBox
    private lateinit var videojuegosBox: CheckBox
    private lateinit var todosBox: CheckBox

    private lateinit var viewModel: OptionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        animeBox = findViewById(R.id.animeCheckBox)
        cineBox = findViewById(R.id.cineCheckBox)
        furryBox = findViewById(R.id.furryCheckBox)
        musicaBox = findViewById(R.id.musicaCheckBox)
        toonsBox = findViewById(R.id.toonsCheckBox)
        videojuegosBox = findViewById(R.id.videojuegosCheckBox)
        todosBox = findViewById(R.id.todosCheckBox)

        viewModel = ViewModelProvider(this).get(OptionsViewModel::class.java)

        viewModel.initializeBoxes(listOf(animeBox,cineBox,furryBox,musicaBox,toonsBox,videojuegosBox,todosBox))

        animeBox.setOnCheckedChangeListener(checkBoxesListener)
        cineBox.setOnCheckedChangeListener(checkBoxesListener)
        furryBox.setOnCheckedChangeListener(checkBoxesListener)
        musicaBox.setOnCheckedChangeListener(checkBoxesListener)
        toonsBox.setOnCheckedChangeListener(checkBoxesListener)
        videojuegosBox.setOnCheckedChangeListener(checkBoxesListener)

        todosBox.setOnCheckedChangeListener { box, boolean ->
            if ( boolean ){
                box.isChecked = false
                animeBox.isChecked = true
                cineBox.isChecked = true
                furryBox.isChecked = true
                musicaBox.isChecked = true
                toonsBox.isChecked = true
                videojuegosBox.isChecked = true
            }
        }


    }

    private val checkBoxesListener = CompoundButton.OnCheckedChangeListener { button, isChecked ->
        viewModel.apply {

            changeTopics(button.id, isChecked)

            if (!isAtLeastOneChecked){
                button.isChecked = true
                changeTopics(button.id, true)
                Toast.makeText(this@OptionsActivity, "Necesitas escoger al menos un tema", Toast.LENGTH_SHORT).show()
            }

            if (isEverythingChecked) {
                todosBox.visibility = View.INVISIBLE
            } else {
                todosBox.visibility = View.VISIBLE
            }
        }
    }

}