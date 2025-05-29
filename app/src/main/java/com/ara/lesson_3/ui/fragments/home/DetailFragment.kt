package com.ara.lesson_3.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun setupListeners() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnDone.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val currentDate = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            App.appDatabase?.noteDao()?.insertNote(NoteModel(title,description, currentDate, currentTime))
            findNavController().navigateUp()
        }
    }
}