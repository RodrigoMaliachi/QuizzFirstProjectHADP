package com.example.quizzfirstprojecthadp.game

import com.example.quizzfirstprojecthadp.MainActivity.Companion.info
import kotlin.random.Random
import kotlin.random.nextInt


class QuestionsInitializer {

    fun getQuestionsInfoList(): List<QuestionInfo> {
        val questionsInfoMutableList = mutableListOf<QuestionInfo>()

        val idTemas: MutableSet<Int> = mutableSetOf() //variable que guarda el numero 'id' correspondientes a cada tema
        info.apply {
            if (anime)
                idTemas.add(0)
            if (cine)
                idTemas.add(1)
            if (furry)
                idTemas.add(2)
            if (deportes)
                idTemas.add(3)
            if (toons)
                idTemas.add(4)
            if (videojuegos)
                idTemas.add(5)
        }

        //Lista con la cantidad de preguntas para cada tema
        val preguntasPorTema = getCantidadDePreguntasPorTema(idTemas.size)

        idTemas.shuffled().forEachIndexed { index, topic ->

            //Lista que servira para obtener aleatoriamente las preguntas
            val intSet = mutableListOf(0,1,2,3,4,5,6,7,8,9)
            intSet.shuffle()

            repeat(preguntasPorTema[index]) {
                val element = intSet.first()
                intSet.remove(element)

                //Genera el id correspondiente a una pregunta
                val questionId = topic * 10 + element

                //Genera 1 de las 24 combinaciones posibles para ordenar las respuestas
                val arranged = Random.nextInt(1..24)

                val newQuestion = QuestionInfo(questionId = questionId, arranged = arranged)

                questionsInfoMutableList.add(newQuestion)
            }
        }

        return questionsInfoMutableList.toList()
    }

    fun getQuestionList(questionsInfoList: List<QuestionInfo>): List<Question> {
        val questionsMutableList = mutableListOf<Question>()
        questionsInfoList.forEach { questionInfo ->
            questionsMutableList.add(Question.listOfQuestion[questionInfo.questionId])
        }
        return questionsMutableList.toList()
    }

    private fun getCantidadDePreguntasPorTema(cantidadDeTemas: Int): List<Int>{
        val listaCantidadPreguntas: MutableList<Int> = mutableListOf()
        val cantidadDePreguntasPorTema = info.questionsQuantity / cantidadDeTemas //La cantididad inicial de preguntas por tema
        val preguntasExtra = info.questionsQuantity % cantidadDeTemas //Solo sirve cuando la división anterior no da como resultado un entero

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
        } else listaCantidadPreguntas.toList()
    }
}