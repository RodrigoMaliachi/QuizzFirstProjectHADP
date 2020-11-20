package com.example.quizzfirstprojecthadp.game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.MainActivity
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

            if (!MainActivity.info.isHintsEnabled) {
                hintsUsedTextView.visibility = View.INVISIBLE
            }

            hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE

            questionNumberTextView.text = questionNumberCounterString
            hintsUsedTextView.text = hintsUsedCounterString
            questionTextView.text = questionsList[currentQuestionIndex].question
            button1.text = option1
            button2.text = option2
            button3.text = option3
            button4.text = option4
            changeRadioGroupStatus()
            updateColors()

            prevButton.setOnClickListener {
                previous()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestionIndex].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
                changeRadioGroupStatus()
                updateColors()
                hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE
            }

            nextButton.setOnClickListener {
                next()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestionIndex].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
                changeRadioGroupStatus()
                updateColors()
                hintButton.visibility = if (isHintClickable) View.VISIBLE else View.INVISIBLE
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

    private val radioButtonListener = CompoundButton.OnCheckedChangeListener { button, isChecked ->
        viewModel.apply {
            if (isChecked && questionsInfoList[currentQuestionIndex].answer == 0) {
                questionsInfoList[currentQuestionIndex].answer =
                    when (button.id) {
                        R.id.optionOne -> 1
                        R.id.optionTwo -> 2
                        R.id.optionThree -> 3
                        else -> 4
                    }
            }
        }
    }

    private fun updateColors() {
        viewModel.apply {
            val answer = questionsInfoList[currentQuestionIndex].answer
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
            } else if (MainActivity.info.isHintsEnabled && currentQuestionInfo.hintsUsedList.isNotEmpty()) {
                currentQuestionInfo.hintsUsedList.forEach {
                    when (it) {
                        1 -> button1.visibility = View.INVISIBLE
                        2 -> button2.visibility = View.INVISIBLE
                        3 -> button3.visibility = View.INVISIBLE
                        4 -> button4.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun changeRadioGroupStatus() {
        viewModel.apply {

            button1.visibility = View.VISIBLE
            button2.visibility = View.VISIBLE
            button3.visibility = View.VISIBLE
            button4.visibility = View.VISIBLE

            if (questionsInfoList[currentQuestionIndex].answer == 0) {
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

                when (questionsInfoList[currentQuestionIndex].answer) {
                    1 -> button1.isChecked = true
                    2 -> button2.isChecked = true
                    3 -> button3.isChecked = true
                    4 -> button4.isChecked = true
                }
            }
        }
    }
}