package com.example.quizzfirstprojecthadp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.quizzfirstprojecthadp.MainActivity.Companion.info
import kotlin.math.max

class EndDialog(private val score: Int): DialogFragment(){

    private lateinit var image: ImageView
    private lateinit var messageTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_end,container,false)

        image = dialogView.findViewById(R.id.finish_game_image)
        messageTextView = dialogView.findViewById(R.id.score)

        val maxScore = info.difficulty * 100.0 * info.questionsQuantity
        when {
            score / maxScore >= 2 / 3.0 -> image.setImageResource(R.drawable.high_score)
            score / maxScore <= 1 / 3.0 -> image.setImageResource(R.drawable.low_score)
            else -> image.setImageResource(R.drawable.medium_score)
        }
        val message = "$score puntos"
        messageTextView.text = message

        return dialogView
    }
}