package com.csarchvz.notesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csarchvz.notesapp.ui.components.NoteActionButtons
import com.csarchvz.notesapp.ui.components.NoteInputFields

import com.csarchvz.notesapp.viewModel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val currentNote = remember { mutableStateOf("") }
    val currentTitle = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            NoteActionButtons(
                onCancel = { navController.popBackStack() },
                onSave = {
                    if (currentTitle.value.isNotEmpty() && currentNote.value.isNotEmpty()) {
                        viewModel.insertNote(
                            title = currentTitle.value,
                            body = currentNote.value,
                        )
                        navController.popBackStack()
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Complete both inputs")
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Create new note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NoteInputFields(
                title = currentTitle.value,
                onTitleChange = { currentTitle.value = it },
                noteBody = currentNote.value,
                onNoteBodyChange = { currentNote.value = it }
            )

        }
    }
}
