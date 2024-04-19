package wep.app.emilioenlaluna.notesapp.state

import wep.app.emilioenlaluna.notesapp.model.Note

data class ListScreenState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val content: String = "",
    val noteId: Int? = null
)

