package com.ara.lesson_3.utils

import com.ara.lesson_3.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}