package com.example.quizzfirstprojecthadp.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
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

            prevButton.setOnClickListener {
                previous()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestion].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
            }

            nextButton.setOnClickListener {
                next()
                questionNumberTextView.text = questionNumberCounterString
                questionTextView.text = questionsList[currentQuestion].question
                button1.text = option1
                button2.text = option2
                button3.text = option3
                button4.text = option4
            }
        }
    }
}