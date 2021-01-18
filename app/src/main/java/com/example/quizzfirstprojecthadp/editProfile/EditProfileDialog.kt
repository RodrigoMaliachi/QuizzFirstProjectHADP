package com.example.quizzfirstprojecthadp.editProfile

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.quizzfirstprojecthadp.R
import com.example.quizzfirstprojecthadp.database.AppDatabase
import java.lang.ClassCastException

class EditProfileDialog : DialogFragment() {

    companion object {
        private const val USER_ID = "USER_ID"

        fun newInstance(userId: Int): EditProfileDialog {
            val editDialog = EditProfileDialog()
            val arguments = Bundle()
            arguments.putInt(USER_ID, userId)
            editDialog.arguments = arguments
            return editDialog
        }
    }

    private lateinit var listener: EditProfileListener

    interface EditProfileListener {
        fun editActiveUser(dialog: EditProfileDialog)
        fun editInactiveUser(dialog: EditProfileDialog)
        fun addNewUser(dialog: EditProfileDialog)
    }

    private lateinit var title: TextView
    private lateinit var profileImage: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button

    private lateinit var image01: ImageButton
    private lateinit var image02: ImageButton
    private lateinit var image03: ImageButton
    private lateinit var image04: ImageButton
    private lateinit var image05: ImageButton
    private lateinit var image06: ImageButton
    private lateinit var image07: ImageButton
    private lateinit var image08: ImageButton
    private lateinit var image09: ImageButton
    private lateinit var image10: ImageButton
    private lateinit var image11: ImageButton
    private lateinit var image12: ImageButton
    private lateinit var image13: ImageButton
    private lateinit var image14: ImageButton
    private lateinit var image15: ImageButton
    private lateinit var image16: ImageButton

    private lateinit var viewModel: EditProfileViewModel

    private val imageListener = View.OnClickListener {
        when (it.id) {
            image01.id -> {
                viewModel.imageResource = 1
                profileImage.setImageResource(R.drawable.avatar_01)
            }
            image02.id -> {
                viewModel.imageResource = 2
                profileImage.setImageResource(R.drawable.avatar_02)
            }
            image03.id -> {
                viewModel.imageResource = 3
                profileImage.setImageResource(R.drawable.avatar_03)
            }
            image04.id -> {
                viewModel.imageResource = 4
                profileImage.setImageResource(R.drawable.avatar_04)
            }
            image05.id -> {
                viewModel.imageResource = 5
                profileImage.setImageResource(R.drawable.avatar_05)
            }
            image06.id -> {
                viewModel.imageResource = 6
                profileImage.setImageResource(R.drawable.avatar_06)
            }
            image07.id -> {
                viewModel.imageResource = 7
                profileImage.setImageResource(R.drawable.avatar_07)
            }
            image08.id -> {
                viewModel.imageResource = 8
                profileImage.setImageResource(R.drawable.avatar_08)
            }
            image09.id -> {
                viewModel.imageResource = 9
                profileImage.setImageResource(R.drawable.avatar_09)
            }
            image10.id -> {
                viewModel.imageResource = 10
                profileImage.setImageResource(R.drawable.avatar_10)
            }
            image11.id -> {
                viewModel.imageResource = 11
                profileImage.setImageResource(R.drawable.avatar_11)
            }
            image12.id -> {
                viewModel.imageResource = 12
                profileImage.setImageResource(R.drawable.avatar_12)
            }
            image13.id -> {
                viewModel.imageResource = 13
                profileImage.setImageResource(R.drawable.avatar_13)
            }
            image14.id -> {
                viewModel.imageResource = 14
                profileImage.setImageResource(R.drawable.avatar_14)
            }
            image15.id -> {
                viewModel.imageResource = 15
                profileImage.setImageResource(R.drawable.avatar_15)
            }
            image16.id -> {
                viewModel.imageResource = 16
                profileImage.setImageResource(R.drawable.avatar_16)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as EditProfileListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement EditProfileListener"))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dialog = inflater.inflate(R.layout.edit_profile_dialog, container, false)
        title = dialog.findViewById(R.id.title)
        profileImage = dialog.findViewById(R.id.profileImage)
        nameEditText = dialog.findViewById(R.id.userName)
        positiveButton = dialog.findViewById(R.id.positiveButton)
        negativeButton = dialog.findViewById(R.id.negativeButton)
        findImageButtons(dialog)
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val context = requireNotNull(context)
        val database = AppDatabase.getInstance(context)
        val factory = EditProfileViewModelFactory(database, arguments?.getInt(USER_ID) ?: -1)
        viewModel = ViewModelProvider(this, factory).get(EditProfileViewModel::class.java)

        if (arguments?.get(USER_ID) == -1) {
            title.text = getString(R.string.add_user)
        }

        profileImage.setImageResource(viewModel.imageResource)
        nameEditText.setText(viewModel.name)

        setImageButtonsListener()

        positiveButton.setOnClickListener {
            when (viewModel.onPositiveClick(nameEditText.text.toString())) {
                -1 -> Toast.makeText(context,R.string.empty_user_name, Toast.LENGTH_SHORT).show()
                0 -> Toast.makeText(context,R.string.taken_user_name, Toast.LENGTH_SHORT).show()
                1 -> {
                    Toast.makeText(context,R.string.user_updated, Toast.LENGTH_SHORT).show()
                    if (viewModel.isActive)
                        listener.editActiveUser(this)
                    else
                        listener.editInactiveUser(this)
                    dismiss()
                }
                2 -> dismiss()
                3 -> {
                    Toast.makeText(context,R.string.user_inserted, Toast.LENGTH_SHORT).show()
                    listener.addNewUser(this)
                    dismiss()
                }
            }
        }

        negativeButton.setOnClickListener { dismiss() }
    }

    private fun findImageButtons(dialog: View) {
        image01 = dialog.findViewById(R.id.avatar01)
        image02 = dialog.findViewById(R.id.avatar02)
        image03 = dialog.findViewById(R.id.avatar03)
        image04 = dialog.findViewById(R.id.avatar04)
        image05 = dialog.findViewById(R.id.avatar05)
        image06 = dialog.findViewById(R.id.avatar06)
        image07 = dialog.findViewById(R.id.avatar07)
        image08 = dialog.findViewById(R.id.avatar08)
        image09 = dialog.findViewById(R.id.avatar09)
        image10 = dialog.findViewById(R.id.avatar10)
        image11 = dialog.findViewById(R.id.avatar11)
        image12 = dialog.findViewById(R.id.avatar12)
        image13 = dialog.findViewById(R.id.avatar13)
        image14 = dialog.findViewById(R.id.avatar14)
        image15 = dialog.findViewById(R.id.avatar15)
        image16 = dialog.findViewById(R.id.avatar16)
    }

    private fun setImageButtonsListener() {
        image01.setOnClickListener(imageListener)
        image02.setOnClickListener(imageListener)
        image03.setOnClickListener(imageListener)
        image04.setOnClickListener(imageListener)
        image05.setOnClickListener(imageListener)
        image06.setOnClickListener(imageListener)
        image07.setOnClickListener(imageListener)
        image08.setOnClickListener(imageListener)
        image09.setOnClickListener(imageListener)
        image10.setOnClickListener(imageListener)
        image11.setOnClickListener(imageListener)
        image12.setOnClickListener(imageListener)
        image13.setOnClickListener(imageListener)
        image14.setOnClickListener(imageListener)
        image15.setOnClickListener(imageListener)
        image16.setOnClickListener(imageListener)
    }
}