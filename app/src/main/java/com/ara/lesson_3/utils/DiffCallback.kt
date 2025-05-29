package com.ara.lesson_3.utils

import androidx.recyclerview.widget.DiffUtil
import com.ara.lesson_3.data.models.NoteModel

class DiffCallback: DiffUtil.ItemCallback<NoteModel>() {

    override fun areItemsTheSame(
        oldItem: NoteModel,
        newItem: NoteModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: NoteModel,
        newItem: NoteModel
    ): Boolean {
        return oldItem == newItem
    }
}