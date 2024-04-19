package wep.app.emilioenlaluna.notesapp

data class ListScreenState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val content: String = "",
    val noteId: Int? = null
)

data class EditNoteState(
    val noteId: Int? = null,
    val title: String = "",
    val content: String = ""
)