package com.example.appnotes.data

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun addNote(note:Note){
        noteDao.addNote(note)
    }

    fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    fun getNoteById(id:Long):Flow<Note>{
        return noteDao.getNoteById(id)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}