package com.example.quizzfirstprojecthadp.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.Player

class ProfileAdapter(private val deleteFunction: (Int) -> Unit,
                     private val changeActivePlayerFunction: (Int) -> Unit,
                     private val editPlayerFunction: (Int) -> Unit
): ListAdapter<Player, ProfileAdapter.ViewHolder>(PlayerDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteFunction, changeActivePlayerFunction, editPlayerFunction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        fun bind(item: Player,
                 f1: (Int) -> Unit,
                 f2: (Int) -> Unit,
                 f3: (Int) -> Unit) {
            val profileImage = (layout.getChildAt(0) as CardView).getChildAt(0) as ImageView
            val playerName = layout.getChildAt(1) as TextView
            val deleteButton = layout.getChildAt(2) as ImageButton

            profileImage.setImageResource(item.getImageResource())
            playerName.text = item.name

            deleteButton.setOnClickListener {
                f1(item.playerId)
            }
            layout.setOnClickListener {
                f2(item.playerId)
            }
            layout.setOnLongClickListener {
                f3(item.playerId)
                true
            }
        }

        companion object {
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.user_item, parent, false) as ConstraintLayout
                return ViewHolder(view)
            }
        }
    }
}

class PlayerDiffCallback : DiffUtil.ItemCallback<Player>(){
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.playerId == newItem.playerId
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}
