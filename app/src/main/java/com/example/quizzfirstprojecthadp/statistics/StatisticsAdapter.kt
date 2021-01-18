package com.example.quizzfirstprojecthadp.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.Score
import com.example.quizzfirstprojecthadp.score.ScoreDiffCallback
import java.text.SimpleDateFormat
import java.util.*

class StatisticsAdapter: ListAdapter<Score, StatisticsAdapter.ViewHolder>(ScoreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val layout: ConstraintLayout): RecyclerView.ViewHolder(layout){
        fun bind(item: Score) {
            val date = layout.getChildAt(0) as TextView
            val hints = layout.getChildAt(1) as TextView
            val score = layout.getChildAt(2) as TextView

            val formatter = SimpleDateFormat("DD/MM", Locale.ENGLISH)
            val dateText = formatter.format(Date(item.date))

            date.text = dateText
            hints.text = item.hintsUsed.toString()
            score.text = item.score.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.statistics_layout, parent, false) as ConstraintLayout

                return ViewHolder(view)
            }
        }
    }
}