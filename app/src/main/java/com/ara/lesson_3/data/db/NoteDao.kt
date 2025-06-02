package com.ara.lesson_3.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ara.lesson_3.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModal: NoteModel)

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<NoteModel>>

    @Delete
    fun deleteNote(noteModal: NoteModel)

    @Update
    fun updateNote(noteModal: NoteModel)

    @Query("SELECT * FROM notes WHERE id=:id")
    fun getNoteById(id: Int): NoteModel?
    
}