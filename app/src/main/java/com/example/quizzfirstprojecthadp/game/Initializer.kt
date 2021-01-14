package com.example.quizzfirstprojecthadp.game

import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.database.Game
import com.example.quizzfirstprojecthadp.database.Question
import com.example.quizzfirstprojecthadp.database.QuestionSaved
import kotlin.random.Random
import kotlin.random.nextInt

class Initializer(val database: AppDatabase) {

    private val playerId = database.playerDao.getActivePlayerForUpdate().playerId
    private val settings = database.settingsDao.getSettingsFromPlayer(playerId)

    private var _game: Game?
    val game: Game
        get() = requireNotNull(_game)

    //Solo se utiliza si se la cantidad de preguntas es menos a 10
    private lateinit var emptyQuestion: QuestionSaved

    private var _questionsSaved: MutableList<QuestionSaved> = mutableListOf()
    val questionSaved: List<QuestionSaved>
        get() = _questionsSaved

    private var _questions: MutableList<Question> = mutableListOf()
    val questions: List<Question>
        get() = _questions

    val difficulty = settings.difficulty

    init {
        _game = database.gameDao.getGame(playerId)
        _game?.let {
            if (it.isFinished) { //Si se terminó el juego, hay que crear uno nuevo

                //Se reinician los valores del nuevo juego
                it.isFinished = false
                it.hints = settings.hintsQuantity
                it.state = 0

                newQuestions()

                //Actualizamos el nuevo juego en la base de datos
                database.gameDao.update(it)
            }
            else { //Sino se termino hay que obtener la información de nuevo
                getTheOldQuestions()
            }
        } ?: newPlayer() //Si la base de datos no tiene información sobre un juego guardado, entonces, el usuario es nuevo
    }

    private fun newPlayer() {
        //Primero a la variable _game le agregaré una nueva instancia de Game con valores predeterminados
        _game = Game(playerId = playerId, isFinished = false, state = 0, hints = settings.hintsQuantity)

        newQuestions()

        database.gameDao.insert(game)

        _game = database.gameDao.getGame(playerId)
    }

    private fun newQuestions(){ //Funcion para un nuevo juego
        val idTemas: MutableSet<Int> = mutableSetOf() //variable que guarda los valores correspondientes a los temas
        settings.apply {
            if (anime)
                idTemas.add(1)
            if (cine)
                idTemas.add(2)
            if (furry)
                idTemas.add(3)
            if (deportes)
                idTemas.add(4)
            if (toons)
                idTemas.add(5)
            if (videojuegos)
                idTemas.add(6)
        }

        //Lista con la cantidad de preguntas para cada tema
        val preguntasPorTema = getCantidadDePreguntasPorTema(idTemas.size)

        idTemas.shuffled().forEachIndexed { index, subject ->

            //Lista que servira para obtener aleatoriamente las preguntas
            val intSet = mutableListOf(0,1,2,3,4,5,6,7,8,9)
            intSet.shuffle()

            repeat(preguntasPorTema[index]) {
                val element = intSet.first()
                intSet.remove(element)

                //Genera el id correspondiente a una pregunta
                val questionId = subject * 10 - element

                //Genera 1 de las 24 combinaciones posibles para ordenar las respuestas
                val arranged =
                        when (settings.difficulty) {
                            1 -> Random.nextInt(1..12)
                            2 -> Random.nextInt(1..18)
                            else -> Random.nextInt(1..24)
                        }

                //Devuelve una 1 de las 10 combinaciones posibles para usar la pista
                val hintOrder =
                    if (settings.hintsEnabled) getHintOrder(arranged, settings.difficulty) else 0
                val newQuestionSaved = QuestionSaved(
                    playerId = playerId,
                    questionId = questionId,
                    arranged = arranged,
                    answered = 0,
                    hintOrder = hintOrder,
                    isHintUsed = false
                )
                database.questionSavedDao.insert(newQuestionSaved)
            }
        }
        if (settings.questionsQuantity<10){
            emptyQuestion = QuestionSaved(
                playerId = playerId,
                questionId = 0,
                arranged = 0,
                answered = 0,
                hintOrder = 0,
                isHintUsed = false
            )
            //Se agrega UNA sola vez a las preguntas guardadas aunque la cantidad de preguntas sea menor a 9
            database.questionSavedDao.insert(emptyQuestion)
        }

        //Se obtienen nuevamente las preguntas guardadas pero ahora con sus respectivos ID'S
        val questionsSavedList = database.questionSavedDao.getQuestionsFromPlayer(playerId)
        questionsSavedList.forEach { questionSaved ->
            if (questionSaved.questionId != 0) {
                _questionsSaved.add(questionSaved)
                _questions.add(
                    database.questionDao.getQuestion(questionSaved.questionId)
                )
            }
        }
        questionsSavedList.forEach { questionSaved ->
            if (questionSaved.questionId == 0)
                repeat(10-_questions.size){
                    _questionsSaved.add(questionSaved)
                }
        }
        _game?.question1 = this._questionsSaved.elementAt(0).questionSavedId
        _game?.question2 = this._questionsSaved.elementAt(1).questionSavedId
        _game?.question3 = this._questionsSaved.elementAt(2).questionSavedId
        _game?.question4 = this._questionsSaved.elementAt(3).questionSavedId
        _game?.question5 = this._questionsSaved.elementAt(4).questionSavedId
        _game?.question6 = this._questionsSaved.elementAt(5).questionSavedId
        _game?.question7 = this._questionsSaved.elementAt(6).questionSavedId
        _game?.question8 = this._questionsSaved.elementAt(7).questionSavedId
        _game?.question9 = this._questionsSaved.elementAt(8).questionSavedId
        _game?.question10 = this._questionsSaved.elementAt(9).questionSavedId

        repeat(10-_questions.size){
            _questionsSaved.removeAt(_questionsSaved.size.minus(1))
        }
    }

    private fun getCantidadDePreguntasPorTema(cantidadDeTemas: Int): List<Int>{
        val listaCantidadPreguntas: MutableList<Int> = mutableListOf()
        val cantidadDePreguntasPorTema = settings.questionsQuantity / cantidadDeTemas //La cantididad inicial de preguntas por tema
        val preguntasExtra = settings.questionsQuantity % cantidadDeTemas //La cantidad sobrante de preguntas para completar la cantidad total

        repeat(cantidadDeTemas) {
            listaCantidadPreguntas.add(cantidadDePreguntasPorTema)
        }

        return if (preguntasExtra != 0){
            repeat(preguntasExtra) {
                //Elegimos un elemento aleatorio de la lista y le agregamos 1 pregunta
                val index = Random.nextInt(0..listaCantidadPreguntas.size.minus(1))
                val nuevaCantidad = listaCantidadPreguntas[index] + 1
                listaCantidadPreguntas.removeAt(index)
                listaCantidadPreguntas.add(nuevaCantidad)
            }
            listaCantidadPreguntas.shuffled()
        } else  listaCantidadPreguntas.toList()
    }

    private fun getHintOrder(arranged: Int, difficulty: Int): Int {
        return if (difficulty == 1)
            when (arranged){
                in 1..6 -> listOf(6,7,9).shuffled().first()
                in 7..12 -> listOf(3,4,9).shuffled().first()
                in 13..18 -> listOf(2,4,7).shuffled().first()
                else -> listOf(2,3,6).shuffled().first()
            }
        else
            when (arranged) {
                in 1..6 -> listOf(5,8,10).shuffled().first()
                in 7..12 -> listOf(1,8,10).shuffled().first()
                in 13..18 -> listOf(1,5,10).shuffled().first()
                else -> listOf(1,5,8).shuffled().first()
            }
    }

    private fun getTheOldQuestions(){
        val questionSavedIdList: MutableList<Int> = mutableListOf()
        _game?.apply {
            questionSavedIdList.add(question1)
            questionSavedIdList.add(question2)
            questionSavedIdList.add(question3)
            questionSavedIdList.add(question4)
            questionSavedIdList.add(question5)
            questionSavedIdList.add(question6)
            questionSavedIdList.add(question7)
            questionSavedIdList.add(question8)
            questionSavedIdList.add(question9)
            questionSavedIdList.add(question10)
        }
        questionSavedIdList.forEach {
            if (it != 0)
                _questionsSaved.add(database.questionSavedDao.getQuestionSaved(it))
        }
        _questionsSaved.forEach {
            _questions.add(database.questionDao.getQuestion(it.questionId))
        }
    }
}