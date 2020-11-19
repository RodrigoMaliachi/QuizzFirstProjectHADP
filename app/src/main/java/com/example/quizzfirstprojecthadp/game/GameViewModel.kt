package com.example.quizzfirstprojecthadp.game

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.MainActivity.Companion.info

class GameViewModel : ViewModel() {

    val initializer = QuestionsInitializer()

    val questionQuantity = info.questionsQuantity

    var currentQuestion = 0

    fun previous() {
        currentQuestion = (currentQuestion - 1 + questionQuantity) % questionQuantity
    }

    fun next() {
        currentQuestion = (currentQuestion + 1 + questionQuantity) % questionQuantity
    }
}