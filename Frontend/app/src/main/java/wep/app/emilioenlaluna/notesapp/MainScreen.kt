package wep.app.emilioenlaluna.notesapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import wep.app.emilioenlaluna.notesapp.ListViewModel
import wep.app.emilioenlaluna.notesapp.Note

@Composable
fun MainScreen(navController: NavController, viewModel: ListViewModel) {
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { navController.navigate("add") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Note")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.notes) { note ->
                NoteItem(
                    note = note,
                    onEdit = { navController.navigate("edit/${note.id}") },
                    onDelete = { viewModel.deleteNote(note) },
                    onDetails = { navController.navigate("detail/${note.id}") }
                )
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onDetails() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${note.title} - ${note.content}")
        Row {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}