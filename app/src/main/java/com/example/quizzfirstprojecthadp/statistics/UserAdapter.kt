package com.example.quizzfirstprojecthadp.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.statistics.StatisticsViewModel.Statistics

class UserAdapter(private val changeStatistics: (Int) -> Unit): ListAdapter<Statistics, UserAdapter.ViewHolder>(StatisticsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, changeStatistics)
    }

    class ViewHolder private constructor(private val layout: ConstraintLayout): RecyclerView.ViewHolder(layout){
        fun bind(
            item: Statistics,
            position: Int,
            function: (Int) -> Unit
        ) {
            val imageView = (layout.getChildAt(0) as CardView).getChildAt(0) as ImageView
            val nameTextView = layout.getChildAt(1) as TextView
            val points = layout.getChildAt(2) as TextView
            imageView.setImageResource(item.player.getImageResource())
            nameTextView.text = item.player.name

            val globalPoints = item.scores.sumBy { it.score }
            points.text = globalPoints.toString()

            layout.setOnClickListener {
                function(position)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.user_statistics_layout, parent, false) as ConstraintLayout
                return ViewHolder(view)
            }
        }
    }
}

class StatisticsDiffCallback: DiffUtil.ItemCallback<Statistics>() {
    override fun areItemsTheSame(oldItem: Statistics, newItem: Statistics): Boolean {
        return oldItem.player == newItem.player
    }

    override fun areContentsTheSame(oldItem: Statistics, newItem: Statistics): Boolean {
        return oldItem == newItem
    }
}