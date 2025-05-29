package com.ara.lesson_3.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.internal.OpDescriptor

@Entity(tableName = "notes")
data class NoteModel(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
