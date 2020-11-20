package com.example.quizzfirstprojecthadp.game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

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

            if (!isHintClickable) {
                hintsUsedTextView.visibility = View.INVISIBLE
                hintButton.visibility = View.INVISIBLE
            }

            questionNumberTextView.text = questionNumberCounterString
            hintsUsedTextView.text = hintsUsedCounterString
            questionTextView.text = questionsList[currentQuestion].question
            button1.text = option1
            button2.text = option2
            button3.text = option3
            button4.text = option4
            changeRadioGroupStatus()
            updateColors()

            prevButton.setOnClickListener {
                previous()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestion].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
                changeRadioGroupStatus()
                updateColors()
            }

            nextButton.setOnClickListener {
                next()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestion].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
                changeRadioGroupStatus()
                updateColors()
            }

            radioGroup.setOnCheckedChangeListener { _, buttonId ->
                val answer: Int
                val buttonSelected: RadioButton

                when (buttonId) {
                    button1.id -> {
                        answer = 1
                        buttonSelected = button1
                    }
                    button2.id -> {
                        answer = 2
                        buttonSelected = button2
                    }
                    button3.id -> {
                        answer = 3
                        buttonSelected = button3
                    }
                    else -> {
                        answer = 4
                        buttonSelected = button4
                    }
                }

                if (buttonSelected.isChecked) {
                    if (questionsInfoList[currentQuestion].answer == 0) {
                        questionsInfoList[currentQuestion].answer = answer
                    }
                    changeRadioGroupStatus()
                    updateColors()
                }
            }
        }
    }

    private fun updateColors() {
        viewModel.apply {
            val answer = questionsInfoList[currentQuestion].answer
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
            }
        }
    }

    private fun changeRadioGroupStatus() {
        viewModel.apply {
            if (questionsInfoList[currentQuestion].answer == 0) {

                button1.isChecked = false
                button2.isChecked = false
                button3.isChecked = false
                button4.isChecked = false

                button1.isClickable = true
                button2.isClickable = true
                button3.isClickable = true
                button4.isClickable = true
            } else {
                when (questionsInfoList[currentQuestion].answer) {
                    1 -> button1.isChecked = true
                    2 -> button2.isChecked = true
                    3 -> button3.isChecked = true
                    4 -> button4.isChecked = true
                }

                button1.isClickable = false
                button2.isClickable = false
                button3.isClickable = false
                button4.isClickable = false
            }
        }

    }
}