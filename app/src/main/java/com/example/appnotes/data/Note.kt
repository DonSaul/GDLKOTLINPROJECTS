package com.example.appnotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note-table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "note-title")
    val title:String= "",
    @ColumnInfo(name = "note-desc")
    val description:String=""
)

object DummyNote{
    val notesList = listOf(Note(title = "Note One", description = "My first note for this app"),
        Note(title = "Note One", description = "My first note for this app"),
        Note(title = "Note two", description = "My second note for this app"),
        Note(title = "Note three", description = "My third note for this app"))
}