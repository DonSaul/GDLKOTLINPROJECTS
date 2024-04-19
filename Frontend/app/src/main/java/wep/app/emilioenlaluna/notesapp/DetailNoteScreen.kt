package wep.app.emilioenlaluna.notesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import wep.app.emilioenlaluna.notesapp.ListViewModel

@Composable
fun DetailNoteScreen(navController: NavController, noteId: Int, viewModel: ListViewModel) {
    val note = viewModel.state.notes.find { it.id == noteId }

    if (note != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = note.title, modifier = Modifier.fillMaxWidth())
            Text(text = note.content, modifier = Modifier.fillMaxWidth())
        }
    }
}