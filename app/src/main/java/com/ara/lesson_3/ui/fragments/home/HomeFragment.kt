package com.ara.lesson_3.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ara.lesson_3.App
import com.ara.lesson_3.R
import com.ara.lesson_3.databinding.FragmentHomeBinding
import com.ara.lesson_3.ui.adapters.HomeAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter = HomeAdapter()

    override fun onCreateView( inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        btnCreate.setOnClickListener {
            val currentDate = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            val bundle = Bundle().apply {
                putString("date", currentDate)
                putString("time", currentTime)
            }

            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
            homeAdapter.submitList(listModel)
        }
    }
}