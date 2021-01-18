package com.example.quizzfirstprojecthadp.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import com.example.quizzfirstprojecthadp.editProfile.EditProfileDialog

class ProfileActivity : AppCompatActivity(), EditProfileDialog.EditProfileListener {

    private lateinit var playersRV: RecyclerView
    private lateinit var activePlayerLayout: LinearLayout
    private lateinit var activePlayerImage: ImageView
    private lateinit var activePlayerName: TextView
    private lateinit var addUser: TextView
    private lateinit var secondDivider: View

    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val database = AppDatabase.getInstance(this)
        val factory = ProfileViewModelFactory(database)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        val deletePlayerFunction: (Int) -> Unit = {
            viewModel.deleteUser(it)
            adapter.submitList(viewModel.inactivePlayers)
            changeSecondDividerVisibility()
        }
        val changeActivePlayerFunction: (Int) -> Unit = {
            viewModel.changeActivePlayer(it)
            adapter.submitList(viewModel.inactivePlayers)
            activePlayerImage.setImageResource(viewModel.activePlayerImage)
            activePlayerName.text = viewModel.activePlayerName
        }
        val editPlayerFunction: (Int) -> Unit = {
            val editDialog = EditProfileDialog.newInstance(it)
            editDialog.show(supportFragmentManager, "Edit Dialog")
        }

        adapter = ProfileAdapter(deletePlayerFunction, changeActivePlayerFunction, editPlayerFunction)

        playersRV = findViewById(R.id.playersRV)
        activePlayerLayout = findViewById(R.id.activeUserLayout)
        activePlayerImage = findViewById(R.id.activePlayerImage)
        activePlayerName = findViewById(R.id.activePlayerName)
        addUser = findViewById(R.id.addUser)
        secondDivider = findViewById(R.id.secondDivider)
        playersRV.adapter = adapter
        adapter.submitList(viewModel.inactivePlayers)
        activePlayerImage.setImageResource(viewModel.activePlayerImage)
        activePlayerName.text = viewModel.activePlayerName

        changeSecondDividerVisibility()

        activePlayerLayout.setOnClickListener {
            val editDialog = EditProfileDialog.newInstance(viewModel.activePlayerId)
            editDialog.show(supportFragmentManager, "Edit Dialog")
        }

        addUser.setOnClickListener {
            val editDialog = EditProfileDialog.newInstance(-1)
            editDialog.show(supportFragmentManager, "Edit Dialog")
        }
    }

    private fun changeSecondDividerVisibility() {
        secondDivider.visibility = if (viewModel.isJustOnePlayer) View.INVISIBLE else View.VISIBLE
    }

    override fun editActiveUser(dialog: EditProfileDialog) {
        activePlayerImage.setImageResource(viewModel.activePlayerImage)
        activePlayerName.text = viewModel.activePlayerName
    }

    override fun editInactiveUser(dialog: EditProfileDialog) {
        adapter.submitList(viewModel.inactivePlayers)
    }

    override fun addNewUser(dialog: EditProfileDialog) {
        activePlayerImage.setImageResource(viewModel.activePlayerImage)
        activePlayerName.text = viewModel.activePlayerName
        adapter.submitList(viewModel.inactivePlayers)
    }

    override fun onBackPressed() {
        setResult(1)
        super.onBackPressed()
    }
}