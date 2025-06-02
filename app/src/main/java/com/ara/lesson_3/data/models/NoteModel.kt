package com.ara.lesson_3.data.models

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteModel(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val color: Int = 0xFFFFF599.toInt()
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
