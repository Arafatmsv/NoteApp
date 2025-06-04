package com.ara.lesson_3.ui.fragments.home

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import com.ara.lesson_3.App
import com.ara.lesson_3.R
import com.ara.lesson_3.data.models.NoteModel
import com.ara.lesson_3.databinding.FragmentDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var noteId: Int = -1
    private var selectedColor: Int = Color.parseColor("#FFF599")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

        val date = arguments?.getString("date") ?: ""
        val time = arguments?.getString("time") ?: ""

        binding.txtDate.text = date
        binding.txtTime.text = time

        updateNote()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }

        if (noteId != -1) {
            val model = App.appDatabase?.noteDao()?.getNoteById(noteId)
            binding.etTitle.setText(model?.title)
            binding.etDescription.setText(model?.description)
            binding.txtDate.text = model?.date
            binding.txtTime.text = model?.time
            selectedColor = model?.color ?: selectedColor
        }
    }

    private fun setupListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnDone.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val data = binding.txtDate.text.toString()
            val time = binding.txtTime.text.toString()

            if (noteId != -1) {
                val updateNote = NoteModel(title, description, data, time, selectedColor)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)

            } else {
                val currentDate = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
                val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                App.appDatabase?.noteDao()?.insertNote(NoteModel(title,description, currentDate, currentTime, selectedColor))
            }
            findNavController().navigateUp()
        }

        btnColors.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.dialog_colors, null)
            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )

            popupWindow.setBackgroundDrawable(null)
            popupWindow.isOutsideTouchable = true


            popupWindow.showAsDropDown(view, 400, 200)

            popupView.findViewById<View>(R.id.color_yellow).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_yellow)
                popupWindow.dismiss()
            }

            popupView.findViewById<View>(R.id.color_purple).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_purple)
                popupWindow.dismiss()
            }

            popupView.findViewById<View>(R.id.color_pink).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_pink)
                popupWindow.dismiss()
            }

            popupView.findViewById<View>(R.id.color_red).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_red)
                popupWindow.dismiss()
            }

            popupView.findViewById<View>(R.id.color_green).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_green)
                popupWindow.dismiss()
            }

            popupView.findViewById<View>(R.id.color_blue).setOnClickListener {
                selectedColor = requireContext().getColor(R.color.color_blue)
                popupWindow.dismiss()
            }
        }

    }
}