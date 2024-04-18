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
import com.csarchvz.notesapp.ui.components.GenericAppBar
import com.csarchvz.notesapp.viewModel.NoteViewModel


import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
    val note = remember {
        mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder)
    }
    val title = note.value.title
    val currentNote = remember { mutableStateOf(note.value.body) }
    val currentTitle = remember { mutableStateOf(note.value.title) }
    val saveButtonState = remember { mutableStateOf(false) }
    val isFabExpanded =
        remember { mutableStateOf(false) } // Estado para manejar la expansión de los FABs


    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: DetailNotePlaceHolder.noteDetailPlaceHolder
            currentTitle.value = note.value.title
            currentNote.value = note.value.body

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit note: $title") },
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

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Clear, "") },
                    text = { Text("Cancel") },
                    onClick = {
                        navController.popBackStack()
                    }
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Check, "") },
                    text = { Text("Save") },
                    onClick = {
                        viewModel.updateNote(
                            NoteEntity(
                                id = noteId,
                                title = currentTitle.value,
                                body = currentNote.value
                            )
                        )
                        navController.popBackStack()
                    }
                )
            }
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(text = "Title note")
                TextField(
                    value = currentTitle.value,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                    ),
                    onValueChange = { value ->
                        currentTitle.value = value
                        saveButtonState.value =
                            currentTitle.value != "" && currentNote.value != ""
                    },
                    label = { Text(text = "Title") }
                )
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "Body note")

                TextField(
                    value = currentNote.value,
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth(),
                    onValueChange = { value ->
                        currentNote.value = value
                        saveButtonState.value =
                            currentTitle.value != "" && currentNote.value != ""
                    },
                    label = { Text(text = "Body") }
                )
            }
        }
    )
}
