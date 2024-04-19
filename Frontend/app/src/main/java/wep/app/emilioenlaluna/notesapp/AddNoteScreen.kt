package wep.app.emilioenlaluna.notesapp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import wep.app.emilioenlaluna.notesapp.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController, viewModel: ListViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = state.title,
            onValueChange = { viewModel.changeTitle(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter Note Title") }
        )

        TextField(
            value = state.content,
            onValueChange = { viewModel.changeContent(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter Note Content") }
        )

        Button(
            onClick = {
                viewModel.createNote()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Note")
        }
    }
}