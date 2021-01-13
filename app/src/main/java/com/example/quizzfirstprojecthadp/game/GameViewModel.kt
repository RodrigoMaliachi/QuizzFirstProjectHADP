package com.example.quizzfirstprojecthadp.game

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.main.MainActivity.Companion.info

class GameViewModel : ViewModel() {

    var score = 0.0
    private val initializer = QuestionsInitializer()

    val questionsInfoList = initializer.getQuestionsInfoList()
    private val questionsList = initializer.getQuestionList(questionsInfoList)
    private val possibleHints = mutableListOf(1,2,3,4)

    private val questionQuantity = info.questionsQuantity
    private val hintsQuantity = info.hintsQuantity

    var currentQuestionIndex = 0
    var currentQuestion = questionsList[currentQuestionIndex]
    var currentQuestionInfo = questionsInfoList[currentQuestionIndex]
    var hintsUsed = 0
    val difficulty = info.difficulty
    var isHintClickable = info.isHintsEnabled && hintsUsed < hintsQuantity && currentQuestionInfo.answer == 0

    var space1 = 0
    var space2 = 0
    var space3 = 0
    var space4 = 0

    var option1 = ""
    var option2 = ""
    var option3 = ""
    var option4 = ""

    val questionNumberCounterString: String
        get() = "Pregunta: ${currentQuestionIndex + 1}/$questionQuantity"

    val hintsUsedCounterString: String
        get() = "Pistas usadas: $hintsUsed/$hintsQuantity"

    init {
        updateOptions()
    }

    fun hintUsed(){
        hintsUsed++

        if (possibleHints.size > 1) {
            currentQuestionInfo.hintsUsedList.add(possibleHints[0])
            possibleHints.removeAt(0)
        } else {
            currentQuestionInfo.answer =
                when {
                    space1 == 1 -> 1
                    space2 == 1 -> 2
                    space3 == 1 -> 3
                    else -> 4
                }
        }

        isHintClickable = info.isHintsEnabled && hintsUsed < hintsQuantity && currentQuestionInfo.answer == 0
    }

    fun previous() {
        currentQuestionIndex = (currentQuestionIndex - 1 + questionQuantity) % questionQuantity
        currentQuestion = questionsList[currentQuestionIndex]
        currentQuestionInfo = questionsInfoList[currentQuestionIndex]
        isHintClickable = info.isHintsEnabled && hintsUsed < hintsQuantity && currentQuestionInfo.answer == 0
        updateOptions()
    }

    fun next() {
        currentQuestionIndex = (currentQuestionIndex + 1 + questionQuantity) % questionQuantity
        currentQuestion = questionsList[currentQuestionIndex]
        currentQuestionInfo = questionsInfoList[currentQuestionIndex]
        isHintClickable = info.isHintsEnabled && hintsUsed < hintsQuantity && currentQuestionInfo.answer == 0
        updateOptions()
    }

    private fun updateOptions() {
        var arrange = currentQuestionInfo.getConvertedArrange()
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

        if (info.isHintsEnabled) {
            updatePossibleHintsList()
        }
    }

    private fun updatePossibleHintsList() {
        possibleHints.clear()
        if (difficulty == 3)
            if (space4 != 1) possibleHints.add(4)

        if (difficulty > 1)
            if (space3 != 1) possibleHints.add(3)

        if (space2 != 1) possibleHints.add(2)
        if (space1 != 1) possibleHints.add(1)

        possibleHints.shuffle()
    }

    private fun optionForSpace(space: Int): String {
        return when (space) {
            1 -> currentQuestion.option1
            2 -> currentQuestion.option2
            3 -> currentQuestion.option3
            else -> currentQuestion.option4
        }
    }

    fun isFinish() : Boolean {
        var questionsAnswered = 0
        questionsInfoList.forEach {
            if (it.answer != 0)
                questionsAnswered++
        }
        return questionsAnswered == questionsInfoList.size
    }

    fun addPoints() {
        score += when {
            currentQuestionInfo.answer == 1 && space1 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
            currentQuestionInfo.answer == 2 && space2 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
            currentQuestionInfo.answer == 3 && space3 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
            currentQuestionInfo.answer == 4 && space4 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
            else -> 0.0
        }
    }
}