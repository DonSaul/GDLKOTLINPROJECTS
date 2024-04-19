package com.example.appnotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnotes.data.Note
import com.example.appnotes.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
): ViewModel(){

    var noteTitleState by mutableStateOf("")
    var noteDescriptionState by mutableStateOf("")

    fun onNoteTitleChanged(newString:String){
        noteTitleState = newString
    }

    fun onNoteDescriptionState(newString: String){
        noteDescriptionState = newString
    }

    lateinit var getAllNotes: Flow<List<Note>>

    init{
        viewModelScope.launch {
            getAllNotes = noteRepository.getNotes()
        }
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note = note)
        }
    }

    fun getNoteById(id:Long):Flow<Note>{
        return noteRepository.getNoteById(id)
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note = note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note = note)
        }
    }
}