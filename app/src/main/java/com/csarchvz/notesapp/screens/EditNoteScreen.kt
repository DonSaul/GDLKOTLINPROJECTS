package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.csarchvz.notesapp.viewModel.NoteViewModel
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.csarchvz.notesapp.data.constants.DetailNotePlaceHolder
import com.csarchvz.notesapp.data.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController,
    noteId: Int
) {
    val scope = rememberCoroutineScope()
    val note = remember { mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder) }

    // This will trigger recomposition when note.value changes, updating the UI accordingly
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
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Clear, contentDescription = "Clear note") },
                    text = { Text("Cancel") },
                    onClick = { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Check, contentDescription = "Save note") },
                    text = { Text("Save") },
                    onClick = {
                        viewModel.updateNote(
                            NoteEntity(
                                id = noteId,
                                title = currentTitle,
                                body = currentNote
                            )
                        )
                        navController.popBackStack()
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = "Title of the note")
            OutlinedTextField(
                value = currentTitle,
                onValueChange = setCurrentTitle,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black, // Color del borde cuando está enfocado
                    unfocusedBorderColor = Color.Gray, // Color del borde cuando no está enfocado
                    containerColor = Color(0xFFF2F2F2) // Gris muy claro para el fondo
                ),
                label = { Text(text = "Title") }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Content of the note")
            OutlinedTextField(
                value = currentNote,
                onValueChange = setCurrentNote,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    containerColor = Color(0xFFF2F2F2)
                ),
                label = { Text(text = "Body") }
            )
        }
    }
}