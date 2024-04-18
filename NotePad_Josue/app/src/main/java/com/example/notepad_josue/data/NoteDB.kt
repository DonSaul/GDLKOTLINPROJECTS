package com.example.notepad_josue.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDB: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        lateinit var database: NoteDB

         fun createDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java, "note_db"
            ).build()
    }
}