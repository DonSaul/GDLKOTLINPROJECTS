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

    private val _currentNote = MutableLiveData<NoteEntity?>()
    val currentNote: LiveData<NoteEntity?> = _currentNote

    fun loadNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = NotesApplication.db.noteDao().getNoteById(noteId)
            _currentNote.postValue(note)
        }
    }


    fun insertNote(title: String, body: String) {

        val noteEntity = NoteEntity(title = title, body = body, list = "none")
        viewModelScope.launch(Dispatchers.IO) {
            NotesApplication.db.noteDao().insertNote(noteEntity)
        }
    }

    fun insertNoteList() {
        val notes = listOf(
            NoteEntity(title = "Uno", body = "UnoBody", list = "none"),
            NoteEntity(title = "Dos", body = "DosBody", list = "none"),
            NoteEntity(title = "Tres", body = "TresBody", list = "none"),
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
            _currentNote.postValue(note)
        }
    }
    // En NoteViewModel

    fun filterNotes(filterText: String): LiveData<List<NoteEntity>> {
        val filteredNotes = notesList.value?.filter { note ->
            note.title.contains(filterText, ignoreCase = true) || note.body.contains(
                filterText, ignoreCase = true
            )
        }
        return MutableLiveData(filteredNotes)
    }

}