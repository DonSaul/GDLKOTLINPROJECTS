package com.csarchvz.notesapp.application

import android.app.Application
import androidx.room.Room
import com.csarchvz.notesapp.data.database.AppDatabase

class NotesApplication : Application() {
    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-user"
        ).build()
    }
}