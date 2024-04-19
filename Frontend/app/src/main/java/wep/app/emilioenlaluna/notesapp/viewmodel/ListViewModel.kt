package wep.app.emilioenlaluna.notesapp.viewmodel

import wep.app.emilioenlaluna.notesapp.service.NoteService
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import wep.app.emilioenlaluna.notesapp.model.Note
import wep.app.emilioenlaluna.notesapp.model.NoteDto
import wep.app.emilioenlaluna.notesapp.state.EditNoteState
import wep.app.emilioenlaluna.notesapp.state.ListScreenState

class ListViewModel(
    private val noteService: NoteService
) : ViewModel() {
    var state by mutableStateOf(ListScreenState())
        private set


    var editNoteState by mutableStateOf(EditNoteState())
        private set

    init {
        getNotes()
    }

    private fun getNotes() {
        println("Agregar Nota private fun getNotes")
        viewModelScope.launch {
            try {
                val notes = noteService.getNotes()
                println("Response from getNotes: $notes")
                state = state.copy(
                    notes = notes
                )
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun editNote(noteId: Int) {
        val note = state.notes.find { it.id == noteId }
        if (note != null) {
            editNoteState = EditNoteState(
                noteId = noteId,
                title = note.title,
                content = note.content
            )
        }
    }

    fun changeEditTitle(title: String) {
        editNoteState = editNoteState.copy(title = title)
    }

    fun changeEditContent(content: String) {
        editNoteState = editNoteState.copy(content = content)
    }

    fun updateNote() {
        val note = NoteDto(
            editNoteState.title,
            editNoteState.content
        )
        viewModelScope.launch {
            try {
                if (editNoteState.noteId != null) {
                    noteService.updateNote(note, editNoteState.noteId!!)
                }
            } catch (e: Exception) {
                println(e)
            }
            getNotes()
            editNoteState = EditNoteState()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                noteService.deleteNote(note.id)
            } catch (e: Exception) {
                println(e)
            }
            getNotes()
        }
    }

    fun createNote() {
        val note = NoteDto(
            state.title,
            state.content
        )
        viewModelScope.launch {
            try {
                if (state.noteId == null) {
                    noteService.insertNote(note)
                    println("Agregar Nota insertNote")
                } else {
                    noteService.updateNote(note, state.noteId!!)
                }
            } catch (e: Exception) {
                println(e)
            }
            getNotes()
            println("Agregar Nota getNotes")
        }
        state = state.copy(
            title = "",
            content = "",
            noteId = null
        )
    }

    fun changeTitle(title: String) {
        state = state.copy(title = title)
    }

    fun changeContent(content: String) {
        state = state.copy(content = content)
    }
}