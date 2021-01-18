package com.example.quizzfirstprojecthadp.statistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase

class StatisticsActivity : AppCompatActivity() {

    private lateinit var userRV: RecyclerView
    private lateinit var statisticsRV: RecyclerView
    private lateinit var name: TextView

    private lateinit var viewModel: StatisticsViewModel
    private lateinit var statisticsAdapter: StatisticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        userRV = findViewById(R.id.usersRV)
        statisticsRV = findViewById(R.id.statisticsRV)
        name = findViewById(R.id.playerStatisticsName)

        val database = AppDatabase.getInstance(this)
        val factory = StatisticsViewModelFactory(database)
        viewModel = ViewModelProvider(this, factory).get(StatisticsViewModel::class.java)

        statisticsAdapter = StatisticsAdapter()
        statisticsAdapter.submitList(viewModel.scores)
        statisticsRV.adapter = statisticsAdapter

        val changeStatistics: (Int) -> Unit = {
            viewModel.index = it
            statisticsAdapter.submitList(viewModel.scores)
            name.text = viewModel.player.name
        }

        val userAdapter = UserAdapter(changeStatistics)
        userAdapter.submitList(viewModel.statistics)
        userRV.adapter = userAdapter

        name.text = viewModel.player.name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.statistics_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.orderByScore -> {
                statisticsAdapter.submitList(viewModel.scores.sortedByDescending { it.score })
                true
            }
            R.id.orderByDate -> {
                statisticsAdapter.submitList(viewModel.scores.sortedByDescending { it.date })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}