package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.constants.DetailNotePlaceHolder
import com.csarchvz.notesapp.viewModel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.data.entities.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailNoteScreen(noteId: Int, viewModel: NoteViewModel, navController: NavController) {
    val scope = rememberCoroutineScope()
    val note = remember {
        mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder)
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: DetailNotePlaceHolder.noteDetailPlaceHolder
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = note.value.title) },
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
                    .padding(16.dp)
            ) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    icon = { Icon(Icons.Filled.Delete, "Delete note") },
                    text = { Text("Delete") },
                    onClick = {
                        viewModel.deleteNote(
                            NoteEntity(
                                id = noteId,
                                title = note.value.title,
                                body = note.value.body
                            )
                        )
                        navController.popBackStack()
                    }
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Edit, "Edit note") },
                    text = { Text("Edit") },
                    onClick = {

                        navController.navigate(NavigationRoutes.noteEditNavigation(noteId))
                    }
                )
            }
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = note.value.title)
            Text(text = note.value.body)
            Text(text = note.value.dateUpdated.toString())
        }
    }
}
