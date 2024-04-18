package com.csarchvz.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csarchvz.notesapp.data.Converters
import com.csarchvz.notesapp.data.dao.NoteDao
import com.csarchvz.notesapp.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}