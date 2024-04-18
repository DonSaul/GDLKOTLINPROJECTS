package wep.app.emilioenlaluna.notesapp


data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val isActive: Boolean,
    val isCompleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val userId: Int,
    val categoryId: Int?
)

data class NoteDto(
    val title: String,
    val content: String
)