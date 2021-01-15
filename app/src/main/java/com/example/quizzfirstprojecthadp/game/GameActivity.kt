package com.example.quizzfirstprojecthadp.game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.EndDialog
import com.example.quizzfirstprojecthadp.main.MainActivity
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase

class GameActivity : AppCompatActivity() {

    private lateinit var questionNumberTextView: TextView
    private lateinit var hintsUsedTextView: TextView

    private lateinit var questionTextView: TextView

    private lateinit var hintButton: Button
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button

    private lateinit var button1: RadioButton
    private lateinit var button2: RadioButton
    private lateinit var button3: RadioButton
    private lateinit var button4: RadioButton
    private lateinit var radioGroup: RadioGroup

    private lateinit var viewModel: GameViewModel
    private lateinit var database: AppDatabase

    companion object {
        var score = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        score = 0.0

        database = AppDatabase.getInstance(this.applicationContext)
        val factory = GameViewModelFactory(database)
        viewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)

        questionNumberTextView = findViewById(R.id.questionNumber)
        hintsUsedTextView = findViewById(R.id.hintsUsed)
        questionTextView = findViewById(R.id.question)
        hintButton = findViewById(R.id.hint)
        prevButton = findViewById(R.id.previous)
        nextButton = findViewById(R.id.next)
        button1 = findViewById(R.id.optionOne)
        button2 = findViewById(R.id.optionTwo)
        button3 = findViewById(R.id.optionThree)
        button4 = findViewById(R.id.optionFour)
        radioGroup = findViewById(R.id.optionsRadioGroup)

        viewModel.apply {

            if (difficulty < 3) {
                button4.visibility = View.INVISIBLE
                if (difficulty == 1)
                    button3.visibility = View.INVISIBLE
            }

            if (hintsQuantity == 0) {
                hintsUsedTextView.visibility = View.INVISIBLE
            }

            hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE

            questionNumberTextView.text = questionNumberCounterString
            hintsUsedTextView.text = hintsUsedCounterString
            questionTextView.text = questionString
            button1.text = option1
            button2.text = option2
            button3.text = option3
            button4.text = option4
            changeRadioGroupStatus()
            updateColors()

            prevButton.setOnClickListener {
                previous()
                updateScreen()
            }

            nextButton.setOnClickListener {
                next()
                updateScreen()
            }

            hintButton.setOnClickListener {
                hintUsed()
                changeRadioGroupStatus()
                updateColors()
                hintsUsedTextView.text = hintsUsedCounterString
                hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE
            }

            radioGroup.setOnCheckedChangeListener { _, _ ->
                changeRadioGroupStatus()
                updateColors()
            }

            button1.setOnCheckedChangeListener(radioButtonListener)
            button2.setOnCheckedChangeListener(radioButtonListener)
            button3.setOnCheckedChangeListener(radioButtonListener)
            button4.setOnCheckedChangeListener(radioButtonListener)
        }
    }

    private fun GameViewModel.updateScreen() {
        questionNumberTextView.text = questionNumberCounterString
        questionTextView.text = questionString
        button1.text = option1
        button2.text = option2
        button3.text = option3
        button4.text = option4
        changeRadioGroupStatus()
        updateColors()
        hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE
    }

    private val radioButtonListener = CompoundButton.OnCheckedChangeListener { button, isChecked ->
        viewModel.apply {
            if (isChecked && this.answer == 0) {
                changeAnswer(button.id)

//                addPoints()

                if (isFinish()) {
                    finish()
                }
            }
        }
    }

    private fun updateColors() {
        viewModel.apply {
            button1.setBackgroundColor(Color.TRANSPARENT)
            button2.setBackgroundColor(Color.TRANSPARENT)
            button3.setBackgroundColor(Color.TRANSPARENT)
            button4.setBackgroundColor(Color.TRANSPARENT)

            if (answer != 0) {
                when {
                    answer == 1 && space1 != 1 -> button1.setBackgroundColor(Color.RED)
                    answer == 2 && space2 != 1 -> button2.setBackgroundColor(Color.RED)
                    answer == 3 && space3 != 1 -> button3.setBackgroundColor(Color.RED)
                    answer == 4 && space4 != 1 -> button4.setBackgroundColor(Color.RED)
                }
                when {
                    space1 == 1 -> button1.setBackgroundColor(Color.GREEN)
                    space2 == 1 -> button2.setBackgroundColor(Color.GREEN)
                    space3 == 1 -> button3.setBackgroundColor(Color.GREEN)
                    space4 == 1 -> button4.setBackgroundColor(Color.GREEN)
                }
            } else if (hintsQuantity > 0 && optionsDisabled.isNotEmpty()) {
                optionsDisabled.forEach {
                    when {
                        space1 == it.toString().toInt() -> button1.visibility = View.INVISIBLE
                        space2 == it.toString().toInt() -> button2.visibility = View.INVISIBLE
                        space3 == it.toString().toInt() -> button3.visibility = View.INVISIBLE
                        space4 == it.toString().toInt() -> button4.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun changeRadioGroupStatus() {
        viewModel.apply {

            button1.visibility = View.VISIBLE
            button2.visibility = View.VISIBLE

            if (difficulty > 1)
                button3.visibility = View.VISIBLE

            if (difficulty > 2)
                button4.visibility = View.VISIBLE

            if (answer == 0) {
                button1.isClickable = true
                button2.isClickable = true
                button3.isClickable = true
                button4.isClickable = true

                button1.isChecked = false
                button2.isChecked = false
                button3.isChecked = false
                button4.isChecked = false
            } else {
                button1.isClickable = false
                button2.isClickable = false
                button3.isClickable = false
                button4.isClickable = false

                when (answer) {
                    1 -> button1.isChecked = true
                    2 -> button2.isChecked = true
                    3 -> button3.isChecked = true
                    4 -> button4.isChecked = true
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuestionIndex()
    }
}