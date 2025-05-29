package com.ara.lesson_3.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ara.lesson_3.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModal: NoteModel)

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<NoteModel>>
}