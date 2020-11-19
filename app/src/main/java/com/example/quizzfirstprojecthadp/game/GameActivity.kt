package com.example.quizzfirstprojecthadp.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.quizzfirstprojecthadp.R

class GameActivity : AppCompatActivity() {

    private lateinit var questionNumberTextView: TextView
    private lateinit var hintsUsedTextView: TextView

    private lateinit var questionTextView: TextView

    private lateinit var prevButton: Button
    private lateinit var nextButton: Button

    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        questionNumberTextView = findViewById(R.id.questionNumber)
        hintsUsedTextView = findViewById(R.id.hintsUsed)
        questionTextView = findViewById(R.id.question)
        prevButton = findViewById(R.id.previous)
        nextButton = findViewById(R.id.next)
        option1 = findViewById(R.id.optionOne)
        option2 = findViewById(R.id.optionTwo)
        option3 = findViewById(R.id.optionThree)
        option4 = findViewById(R.id.optionFour)

        viewModel.apply {

//            questionNumberTextView.text =
            questionTextView.text = questionsList[currentQuestion].question

            prevButton.setOnClickListener {
                next()
                questionTextView.text = questionsList[currentQuestion].question
            }

            nextButton.setOnClickListener {
                previous()
                questionTextView.text = questionsList[currentQuestion].question
            }
        }


    }
}