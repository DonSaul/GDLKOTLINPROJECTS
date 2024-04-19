package wep.app.emilioenlaluna.notesapp.model


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

