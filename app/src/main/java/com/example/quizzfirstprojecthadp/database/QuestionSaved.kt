package com.example.quizzfirstprojecthadp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "questions_saved")
data class QuestionSaved(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_saved_id")
    var questionSavedId: Int = 0,

    @ForeignKey(
        entity = Player::class,
        parentColumns = ["player_id"],
        childColumns = ["player_id"]
    )
    @ColumnInfo(name = "player_id")
    var playerId: Int = 0,

    @ForeignKey(
        entity = Question::class,
        parentColumns = ["question_id"],
        childColumns = ["question_id"]
    )
    @ColumnInfo(name = "question_id")
    var questionId: Int = 0,

    @ColumnInfo(name = "arranged")
    var arranged: Int = 0,
    /*Orden:
        1       1234
        2       1243
        3       1324
        4       1423    //easy -> 6,7,9
        5       1342    //normal -> 5,8,10
        6       1432    // 1 - 6 space 1
        7       2134
        8       2143
        9       3124
        10      4123    //easy -> 3,4,9
        11      3142    //normal -> 1,8,10
        12      4132    // 7 - 12 space 2
        13      2314
        14      2413
        15      3214
        16      4213    //easy -> 2,4,7
        17      3412    //normal -> 1,5,10
        18      4312    // 13 - 18 space 3
        19      2341
        20      2431
        21      3241
        22      4231    //easy -> 2,3,6
        23      3421    //normal -> 1,5,8
        24      4321    // 19 - 24 space 4
     */

    @ColumnInfo(name = "answered")
    var answered: Int = 0,
    /*
        0   initialize
        1   option 1
        2   option 2
        3   option 3
        4   option 4
    */

    @ColumnInfo(name = "hint_order")
    var hintOrder: Int = 0,
    /* Hint order
        0 is for difficulty == hard
        01   1000   normal 2,3,4
        02   1100   easy 3,4
        03   1010   easy 2,4
        04   1001   easy 2,3
        05   0100   normal 1,3,4
        06   0110   easy 1,4
        07   0101   easy 1,3
        08   0010   normal 1,2,4
        09   0011   easy 1,2
        10   0001   normal 1,2,3
    */

    @ColumnInfo(name = "is_hint_used", typeAffinity = ColumnInfo.INTEGER)
    var isHintUsed: Boolean = false
)