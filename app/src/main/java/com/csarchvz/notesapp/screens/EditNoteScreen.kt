package com.csarchvz.notesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import com.csarchvz.notesapp.data.constants.DetailNotePlaceHolder
import com.csarchvz.notesapp.data.entities.NoteEntity
import com.csarchvz.notesapp.ui.components.NoteActionButtons
import com.csarchvz.notesapp.ui.components.NoteInputFields
import com.csarchvz.notesapp.viewModel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController,
    noteId: Int
) {
    val scope = rememberCoroutineScope()
    val note = remember { mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder) }

    val (currentTitle, setCurrentTitle) = remember { mutableStateOf(note.value.title) }
    val (currentNote, setCurrentNote) = remember { mutableStateOf(note.value.body) }

    LaunchedEffect(key1 = noteId) {
        scope.launch(Dispatchers.IO) {
            val loadedNote = viewModel.getNote(noteId)
            if (loadedNote != null) {
                note.value = loadedNote
                setCurrentTitle(loadedNote.title)
                setCurrentNote(loadedNote.body)
            }
        }
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            NoteActionButtons(
                onCancel = { navController.popBackStack() },
                onSave = {
                    viewModel.updateNote(
                        NoteEntity(
                            id = noteId,
                            title = currentTitle,
                            body = currentNote,
                            list = "none"
                        )
                    )
                    navController.popBackStack()
                },
                saveEnabled = false
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit note") },
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
                title = currentTitle,
                onTitleChange = setCurrentTitle,
                noteBody = currentNote,
                onNoteBodyChange = setCurrentNote
            )
        }
    }
}
