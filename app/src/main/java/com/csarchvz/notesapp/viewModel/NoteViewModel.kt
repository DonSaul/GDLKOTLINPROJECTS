package com.csarchvz.notesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csarchvz.notesapp.application.NotesApplication
import com.csarchvz.notesapp.data.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class NoteViewModel : ViewModel() {

    val notesList: LiveData<List<NoteEntity>> = NotesApplication.db.noteDao().getNotes()

    fun insertNote(title: String, body: String) {

        val noteEntity = NoteEntity(title = title, body = body)
        viewModelScope.launch(Dispatchers.IO) {
            NotesApplication.db.noteDao().insertNote(noteEntity)
        }
    }

    fun insertNoteList() {
        val notes = listOf(
            NoteEntity(title = "Uno", body = "UnoBody"),
            NoteEntity(title = "Dos", body = "DosBody"),
            NoteEntity(title = "Tres", body = "TresBody"),
        )

        viewModelScope.launch(Dispatchers.IO) {
            NotesApplication.db.noteDao().insertListNotes(notes)
        }
    }

    suspend fun getNote(noteId: Int): NoteEntity? {
        return NotesApplication.db.noteDao().getNoteById(noteId)
    }

    fun deleteNote(note: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            NotesApplication.db.noteDao().deleteNote(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            NotesApplication.db.noteDao().updateNote(note)
        }
    }

    // En NoteViewModel

    fun filterNotes(filterText: String): LiveData<List<NoteEntity>> {
        val filteredNotes = notesList.value?.filter { note ->
            note.title.contains(filterText, ignoreCase = true) || note.body.contains(
                filterText,
                ignoreCase = true
            )
        }
        return MutableLiveData(filteredNotes)
    }

}