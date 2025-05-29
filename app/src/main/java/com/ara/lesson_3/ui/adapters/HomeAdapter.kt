package com.ara.lesson_3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ara.lesson_3.data.models.NoteModel
import com.ara.lesson_3.databinding.FragmentDetailBinding
import com.ara.lesson_3.databinding.ItemNoteBinding
import com.ara.lesson_3.utils.DiffCallback

class HomeAdapter : ListAdapter<NoteModel, HomeAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtItemTitle.text = item.title
            binding.txtItemDescription.text = item.description
            binding.txtItemDate.text = item.date
            binding.txtItemTime.text = item.time
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}