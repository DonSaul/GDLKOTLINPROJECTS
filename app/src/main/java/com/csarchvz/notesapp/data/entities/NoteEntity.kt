package com.csarchvz.notesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name="body")
    val body: String,

    @ColumnInfo(name="dateUpdated")
    val dateUpdated: Date = Date.from(Instant.now())
)
