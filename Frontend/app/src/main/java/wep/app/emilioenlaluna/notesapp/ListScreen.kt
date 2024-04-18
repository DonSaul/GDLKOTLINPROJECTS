package wep.app.emilioenlaluna.notesapp

import ListViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Mis Notas", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        TextField(
            value = state.title,
            onValueChange = { viewModel.changeTitle(it) },
            placeholder = { "Titulo"}
        )
        TextField(
            value = state.content,
            onValueChange = { viewModel.changeContent(it) },
            placeholder = {"Contenido"}
        )
        Button(onClick = { viewModel.createNote() }) {
            Text(text = "Agregar Nota")
            println("Agregar Nota button")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.notes) {
                NoteItem(note = it, modifier = Modifier.fillMaxWidth(), onEdit = {
                    viewModel.editNote(it)
                }, onDelete = {
                    viewModel.deleteNote(it)
                })
            }
        }
    }
}