package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.constants.DetailNotePlaceHolder
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.data.entities.NoteEntity
import com.csarchvz.notesapp.viewModel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailNoteScreen(noteId: Int, viewModel: NoteViewModel, navController: NavController) {
    val scope = rememberCoroutineScope()
    val note = remember { mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder) }
    val showListDialog = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("") }
    val showDeleteConfirmDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = noteId) {
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: DetailNotePlaceHolder.noteDetailPlaceHolder
            selectedOption.value = note.value.list
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = note.value.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
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
                        showDeleteConfirmDialog.value = true
                    }
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    containerColor = MaterialTheme.colorScheme.secondary,
                    icon = { Icon(Icons.Filled.Edit, "Edit note") },
                    text = { Text("Edit") },
                    onClick = {
                        navController.navigate(NavigationRoutes.noteEditNavigation(noteId))
                    }
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    icon = { Icon(Icons.Filled.Edit, "Set note to a list") },
                    text = { Text("Set to list") },
                    onClick = {
                        showListDialog.value = true
                    }
                )
            }
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = note.value.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Text(
                text = note.value.body,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "List: ",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Red
                    ), // Use bolder font weight for title
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = note.value.list)
            }
            Text(
                text = "Last updated: ${note.value.dateUpdated}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        if (showListDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showListDialog.value = false
                },
                title = {
                    Text(text = "Set to list")
                },
                text = {
                    Column {
                        listOf("Home", "School", "Work", "Life").forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                RadioButton(
                                    selected = selectedOption.value == option,
                                    onClick = { selectedOption.value = option }
                                )
                                Text(text = option, modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showListDialog.value = false
                            viewModel.updateNote(
                                NoteEntity(
                                    id = noteId,
                                    title = note.value.title,
                                    body = note.value.title,
                                    list = selectedOption.value,
                                )
                            )

                            navController.navigate(NavigationRoutes.NAVIGATION_HOME)
                        }
                    ) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showListDialog.value = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }

        if (showDeleteConfirmDialog.value) {
            AlertDialog(
                onDismissRequest = { showDeleteConfirmDialog.value = false },
                title = { Text(text = "Confirmar eliminación") },
                text = { Text(text = "¿Estás seguro de que deseas eliminar esta nota?") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteNote(
                                NoteEntity(
                                    id = noteId,
                                    title = note.value.title,
                                    body = note.value.body,
                                    list = "none"
                                )
                            )
                            showDeleteConfirmDialog.value = false
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red) // Botón en rojo para confirmar la eliminación
                    ) {
                        Text("Eliminar", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDeleteConfirmDialog.value = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
