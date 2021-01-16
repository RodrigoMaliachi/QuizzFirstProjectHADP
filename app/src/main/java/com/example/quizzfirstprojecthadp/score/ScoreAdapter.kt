package com.example.quizzfirstprojecthadp.score

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.Score

class ScoreAdapter(private val names: List<String>, private val data: List<Score>) : ListAdapter<Score,
        ScoreAdapter.ViewHolder>(ScoreDiffCallback()) {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position, names)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val layout: ConstraintLayout)
        : RecyclerView.ViewHolder(layout) {

        fun bind(item: Score, position: Int, names: List<String>) {
            val placeNumber = layout.getChildAt(0) as TextView
            val playerName = layout.getChildAt(1) as TextView
            val star = layout.getChildAt(2) as ImageView
            val score = layout.getChildAt(3) as TextView
            val s = "${position + 1}°"
            placeNumber.text = s
            playerName.text = names[position]
            if (item.hintsWasUsed) {
                star.visibility = View.GONE
            }
            score.text = item.score.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.top_score_layout, parent, false) as ConstraintLayout

                return ViewHolder(view)
            }
        }
    }
}

class ScoreDiffCallback : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem.scoreId == newItem.scoreId
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }
}