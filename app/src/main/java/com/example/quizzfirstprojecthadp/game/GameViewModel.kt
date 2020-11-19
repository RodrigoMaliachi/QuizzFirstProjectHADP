package com.example.quizzfirstprojecthadp.game

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.MainActivity.Companion.info

class GameViewModel : ViewModel() {

    private val initializer = QuestionsInitializer()

    val questionsInfoList = initializer.getQuestionsInfoList()
    val questionsList = initializer.getQuestionList(questionsInfoList)

    private val questionQuantity = info.questionsQuantity
    private val hintsQuantity = info.hintsQuantity

    var currentQuestion = 0
    var hintsUsed = 0
    val difficulty = info.difficulty
    val isHintClickable = info.isHintsEnabled

    private var space1 = 0
    private var space2 = 0
    private var space3 = 0
    private var space4 = 0

    var option1 = ""
    var option2 = ""
    var option3 = ""
    var option4 = ""

    val questionNumberCounterString: String
        get() = "Pregunta: ${currentQuestion + 1}/$questionQuantity"

    val hintsUsedCounterString: String
        get() = "Pistas usadas: ${hintsUsed + 1}/$hintsQuantity"

    init {
        updateOptions()
    }

    fun previous() {
        currentQuestion = (currentQuestion - 1 + questionQuantity) % questionQuantity
        updateOptions()
    }

    fun next() {
        currentQuestion = (currentQuestion + 1 + questionQuantity) % questionQuantity
        updateOptions()
    }

    private fun updateOptions() {
        var arrange = questionsInfoList[currentQuestion].getConvertedArrange()
        space1 = arrange / 1000
        arrange -= space1 * 1000
        space2 = arrange / 100
        arrange -= space2 * 100
        space3 = arrange / 10
        arrange -= space3 * 10
        space4 = arrange
        option1 = optionForSpace(space1)
        option2 = optionForSpace(space2)
        option3 = optionForSpace(space3)
        option4 = optionForSpace(space4)
    }

    private fun optionForSpace(space: Int): String {
        return when (space) {
            1 -> questionsList[currentQuestion].option1
            2 -> questionsList[currentQuestion].option2
            3 -> questionsList[currentQuestion].option3
            else -> questionsList[currentQuestion].option4
        }
    }
}