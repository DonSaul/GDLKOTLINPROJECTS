package com.example.appnotes

import android.content.Context
import androidx.room.Room
import com.example.appnotes.data.NoteDatabase
import com.example.appnotes.data.NoteRepository

object Graph {
    lateinit var database: NoteDatabase

    val noteRepository by lazy {
        NoteRepository(noteDao = database.noteDao())
    }

    fun provide(context:Context){
        database = Room.databaseBuilder(context, NoteDatabase::class.java, "notes.db").build()
    }
}