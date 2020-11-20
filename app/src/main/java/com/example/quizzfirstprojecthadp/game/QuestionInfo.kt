package com.example.quizzfirstprojecthadp.game

class QuestionInfo (val questionId: Int, private val arranged: Int, var answer: Int = 0, val hintsUsedList: MutableList<Int> = mutableListOf()){

    fun getConvertedArrange() =
        when (arranged) {
            1 -> 1234
            2 -> 1243
            3 -> 1324
            4 -> 1423
            5 -> 1342
            6 -> 1432
            7 -> 2134
            8 -> 2143
            9 -> 3124
            10 -> 4123
            11 -> 3142
            12 -> 4132
            13 -> 2314
            14 -> 2413
            15 -> 3214
            16 -> 4213
            17 -> 3412
            18 -> 4312
            19 -> 2341
            20 -> 2431
            21 -> 3241
            22 -> 4231
            23 -> 3421
            else -> 4321
        }
}
