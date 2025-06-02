package com.ara.lesson_3.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ara.lesson_3.App
import com.ara.lesson_3.R
import com.ara.lesson_3.data.models.NoteModel
import com.ara.lesson_3.databinding.FragmentHomeBinding
import com.ara.lesson_3.ui.adapters.HomeAdapter
import com.ara.lesson_3.utils.OnClickItem
import com.ara.lesson_3.utils.PreferenceHelper
import com.ara.lesson_3.utils.PreferenceHelper.isLinear
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment(R.layout.fragment_home), OnClickItem {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter = HomeAdapter(this, this)
    private var isLinear: Boolean = true


    override fun onCreateView( inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isLinear = PreferenceHelper.isLinear()
        binding.rvNote.layoutManager =
            if (isLinear) {
                LinearLayoutManager(requireContext())
            } else {
                GridLayoutManager(requireContext(), 2)
            }

        binding.btnShape.setImageResource(
            if (isLinear) R.drawable.ic_grid else R.drawable.ic_shape
        )

        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() {
        binding.rvNote.apply {
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

        btnShape.setOnClickListener {
            isLinear = !isLinear
            PreferenceHelper.setLinear(isLinear)

            binding.rvNote.layoutManager =
                if (isLinear) {
                    LinearLayoutManager(requireContext())
                } else {
                    GridLayoutManager(requireContext(), 2)
                }

            binding.btnShape.setImageResource(
                if (isLinear) R.drawable.ic_grid else R.drawable.ic_shape
            )
        }

    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
            homeAdapter.submitList(listModel)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_delete, null)
        val builder = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        builder.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        val btnDelete = dialogView.findViewById<Button>(R.id.btn_delete)

        btnCancel.setOnClickListener {
            builder.cancel()
        }

        btnDelete.setOnClickListener {
            App.appDatabase?.noteDao()?.deleteNote(noteModel)
            builder.dismiss()
        }

        builder.show()
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }


}