package com.example.quizzfirstprojecthadp.game

import androidx.lifecycle.ViewModel
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Game
import com.example.quizzfirstprojecthadp.database.Question
import com.example.quizzfirstprojecthadp.database.QuestionSaved

class GameViewModel(val database: AppDatabase) : ViewModel() {

    private val initializer = Initializer(database)
    private val game: Game

    private val questionsSaved: List<QuestionSaved>
    private val questions: List<Question>

    private var qIndex: Int //questionIndex

    private val questionQuantity: Int
        get() = questions.size

    private val cQuestion //currentQuestion
        get() = questions[qIndex]

    private val cQuestionSaved: QuestionSaved //currentQuestionSaved
        get() = questionsSaved[qIndex]

    val difficulty: Int
        get() = initializer.difficulty
    val hintsQuantity: Int
        get() = game.hints
    val isHintClickable
        get() = game.hintsUsed < hintsQuantity && cQuestionSaved.answer == 0
    val questionString
        get() = questions[qIndex].question
    val answer
        get() = questionsSaved[qIndex].answer
    val optionsDisabled
        get() = questionsSaved[qIndex].optionsDisabled.toList()

    var space1 = 0
    var space2 = 0
    var space3 = 0
    var space4 = 0

    var option1 = ""
    var option2 = ""
    var option3 = ""
    var option4 = ""

    val questionNumberCounterString: String
        get() = "Pregunta: ${qIndex + 1}/$questionQuantity"

    val hintsUsedCounterString: String
        get() = "Pistas usadas: ${game.hintsUsed}/$hintsQuantity"

    init {
        game = initializer.game
        questionsSaved = initializer.questionSaved
        questions = initializer.questions.toMutableList()
        qIndex = game.questionIndex
        updateOptions()
    }

    fun hintUsed(){
        game.hintsUsed++
        val arrange = cQuestionSaved.getConvertedArrange().toString().toMutableList()
        if (difficulty < 3) {
            arrange.removeAt(3)
            if (difficulty < 2)
                arrange.removeAt(2)
        }
        arrange.remove('1')
        cQuestionSaved.optionsDisabled.forEach { arrange.remove(it) }
        questionsSaved[qIndex].optionsDisabled += arrange.shuffled().first().toString()
        if (cQuestionSaved.optionsDisabled.length == difficulty)
            questionsSaved[qIndex].answer = when {
                space1 == 1 -> 1
                space2 == 1 -> 2
                space3 == 1 -> 3
                else -> 4
            }
        database.gameDao.update(game)
        database.questionSavedDao.update(cQuestionSaved)
    }

    fun previous() {
        qIndex = (qIndex - 1 + questionQuantity) % questionQuantity
        updateOptions()
    }

    fun next() {
        qIndex = (qIndex + 1 + questionQuantity) % questionQuantity
        updateOptions()
    }

    private fun updateOptions() {
        var arrange = cQuestionSaved.getConvertedArrange()
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
            1 -> cQuestion.option1
            2 -> cQuestion.option2
            3 -> cQuestion.option3
            else -> cQuestion.option4
        }
    }

    fun isFinish() : Boolean {
        var questionsAnswered = 0
        questionsSaved.forEach {
            if (it.answer != 0)
                questionsAnswered++
        }
        database.questionSavedDao.update(cQuestionSaved)
        game.isFinished = questionsAnswered == questionQuantity
        if (game.isFinished) {
            database.gameDao.update(game)
        }
        return game.isFinished
    }

    fun changeAnswer(id: Int) {
        questionsSaved[qIndex].answer = when (id) {
            R.id.optionOne -> 1
            R.id.optionTwo -> 2
            R.id.optionThree -> 3
            else -> 4
        }
    }

    fun saveQuestionIndex() {
        game.questionIndex = qIndex
        database.gameDao.update(game)
    }

//    fun addPoints() {
//        score += when {
//            currentQuestionInfo.answer == 1 && space1 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
//            currentQuestionInfo.answer == 2 && space2 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
//            currentQuestionInfo.answer == 3 && space3 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
//            currentQuestionInfo.answer == 4 && space4 == 1 -> difficulty * 100.0 /(currentQuestionInfo.hintsUsedList.size + 1)
//            else -> 0.0
//        }
//    }
}