package com.csarchvz.notesapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.csarchvz.notesapp.data.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity WHERE id=:id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity ORDER BY dateUpdated DESC")
    fun getNotes(): LiveData<List<NoteEntity>>

    @Delete
    fun deleteNote(note: NoteEntity): Int

    @Update
    fun updateNote(note: NoteEntity): Int

    @Insert
    fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertListNotes(notes: List<NoteEntity>): List<Long>
}