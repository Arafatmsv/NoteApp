package com.ara.lesson_3.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ara.lesson_3.data.models.NoteModel

@Database(entities = [NoteModel::class ], version = 2)
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}