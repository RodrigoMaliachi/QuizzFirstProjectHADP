package com.example.quizzfirstprojecthadp.options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.SettingsDao
import com.example.quizzfirstprojecthadp.main.MainActivity
import com.example.quizzfirstprojecthadp.main.MainActivity.Companion.PLAYER_ID

class OptionsActivity : AppCompatActivity() {

    private lateinit var animeBox: CheckBox
    private lateinit var cineBox: CheckBox
    private lateinit var furryBox: CheckBox
    private lateinit var deportesBox: CheckBox
    private lateinit var toonsBox: CheckBox
    private lateinit var videojuegosBox: CheckBox
    private lateinit var todosBox: CheckBox

    private lateinit var questionSpinner: Spinner
    private lateinit var hintsSpinner: Spinner

    private lateinit var difficultyRadioButton: RadioGroup

    private lateinit var hintSwitch: SwitchCompat
    private lateinit var hintSpinnerLayout: LinearLayout

    private lateinit var viewModel: OptionsViewModel
    private lateinit var database: SettingsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        val playerId = intent.getIntExtra(PLAYER_ID, 0)
        database = AppDatabase.getInstance(this.applicationContext).settingsDao
        val factory = OptionsViewModelFactory(database, playerId)
        viewModel = ViewModelProvider(this, factory).get(OptionsViewModel::class.java)

        animeBox = findViewById(R.id.animeCheckBox)
        cineBox = findViewById(R.id.cineCheckBox)
        furryBox = findViewById(R.id.furryCheckBox)
        deportesBox = findViewById(R.id.deportesCheckBox)
        toonsBox = findViewById(R.id.toonsCheckBox)
        videojuegosBox = findViewById(R.id.videojuegosCheckBox)
        todosBox = findViewById(R.id.todosCheckBox)

        //Inicializa el adaptador del Spinner de preguntas y selecciona la opción guardada en info a través del ViewModel
        questionSpinner = findViewById(R.id.questionSpinner)
        questionSpinner.adapter = viewModel.spinnerAdapter(this, R.array.question_spinner)
        questionSpinner.setSelection(viewModel.questionsQuantity.minus(5))

        //Inicializa el adaptador del Spinner de pistas y selecciona la opción guardada en info a través del ViewModel
        hintsSpinner = findViewById(R.id.hintsSpinner)
        hintsSpinner.adapter = viewModel.spinnerAdapter(this, R.array.hints_spinner)
        hintsSpinner.setSelection(viewModel.hintsQuantity.minus(1))

        //Selecciona la dificultad guardada
        difficultyRadioButton = findViewById(R.id.difficultyRadioGroup)
        difficultyRadioButton.check(idDifficultyButton())

        //Activa o desactiva las pistas segun la info guardada
        hintSwitch = findViewById(R.id.hintSwitch)
        hintSpinnerLayout = findViewById(R.id.hintsSpinnerLayout)
        viewModel.isHintsEnabled.let {
            hintSwitch.isChecked = it
            hintSpinnerLayout.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        viewModel.initializeBoxes(listOf(animeBox,cineBox,furryBox,deportesBox,toonsBox,videojuegosBox,todosBox))

        animeBox.setOnCheckedChangeListener(checkBoxesListener)
        cineBox.setOnCheckedChangeListener(checkBoxesListener)
        furryBox.setOnCheckedChangeListener(checkBoxesListener)
        deportesBox.setOnCheckedChangeListener(checkBoxesListener)
        toonsBox.setOnCheckedChangeListener(checkBoxesListener)
        videojuegosBox.setOnCheckedChangeListener(checkBoxesListener)

        todosBox.setOnCheckedChangeListener { box, boolean ->
            if ( boolean ){
                box.isChecked = false
                animeBox.isChecked = true
                cineBox.isChecked = true
                furryBox.isChecked = true
                deportesBox.isChecked = true
                toonsBox.isChecked = true
                videojuegosBox.isChecked = true
            }
        }

        questionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.questionsQuantity = position + 5
            }
        }
        hintsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.hintsQuantity = position + 1
            }
        }

        difficultyRadioButton.setOnCheckedChangeListener { _, idButton ->
            when (idButton) {
                R.id.highButton -> viewModel.difficulty = 3
                R.id.mediumButton -> viewModel.difficulty = 2
                R.id.lowButton -> viewModel.difficulty = 1
            }
        }

        hintSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isHintsEnabled = isChecked
            hintSpinnerLayout.visibility = if (isChecked) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun idDifficultyButton() =
        when (viewModel.difficulty) {
            3 -> R.id.highButton
            2 -> R.id.mediumButton
            else -> R.id.lowButton
        }

    private val checkBoxesListener = CompoundButton.OnCheckedChangeListener { button, isChecked ->
        viewModel.apply {

            //Cambia la información guardada del tema seleccionado en info
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

    override fun onPause() {
        super.onPause()
        database.update(viewModel.settings)
    }
}