package com.csarchvz.notesapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.data.entities.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNoteTopBar(navController: NavController, noteTitle: String) {
    TopAppBar(
        title = { Text(text = noteTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun DetailNoteFloatingActionButtons(
    onDeleteClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onSetToListClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier.padding(10.dp, 0.dp),
            containerColor = MaterialTheme.colorScheme.error,
            icon = { Icon(Icons.Filled.Delete, "Delete note") },
            text = { Text("Delete") },
            onClick = {
                onDeleteClicked()
            }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier.padding(10.dp, 0.dp),
            containerColor = MaterialTheme.colorScheme.secondary,
            icon = { Icon(Icons.Filled.Edit, "Edit note") },
            text = { Text("Edit") },
            onClick = {
                onEditClicked()
            }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier.padding(10.dp, 0.dp),
            containerColor = MaterialTheme.colorScheme.tertiary,
            icon = { Icon(Icons.Filled.Edit, "Set note to a list") },
            text = { Text("Set to list") },
            onClick = {
                onSetToListClicked()
            }
        )
    }
}

@Composable
fun NoteContent(modifier: Modifier = Modifier, note: NoteEntity) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = note.title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = note.body,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "List: ${note.list}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Last updated: ${note.dateUpdated}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ListSelectionDialog(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Set to list") },
        text = {
            Column {
                listOf("Home", "School", "Work", "Life").forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { onOptionSelected(option) }
                        )
                        Text(text = option, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Confirm deletion") },
        text = { Text(text = "Are you sure you want to delete this note?") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Delete", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
