import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import wep.app.emilioenlaluna.notesapp.ListScreenState
import wep.app.emilioenlaluna.notesapp.Note
import wep.app.emilioenlaluna.notesapp.NoteDto

class ListViewModel(
    private val noteService: NoteService
) : ViewModel() {
    var state by mutableStateOf(ListScreenState())
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

    fun changeTitle(title: String) {
        state = state.copy(
            title = title
        )
    }

    fun changeContent(content: String) {
        state = state.copy(
            content = content
        )
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                noteService.deleteNote(note.id)
            } catch (e: Exception) {
                println()
            }
            getNotes()
        }
    }

    fun editNote(note: Note) {
        state = state.copy(
            title = note.title,
            content = note.content,
            noteId = note.id
        )
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
}