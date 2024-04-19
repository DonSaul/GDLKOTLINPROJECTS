package wep.app.emilioenlaluna.notesapp.state

data class EditNoteState(
    val noteId: Int? = null,
    val title: String = "",
    val content: String = ""
)