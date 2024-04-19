package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.constants.DetailNotePlaceHolder
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.data.entities.NoteEntity
import com.csarchvz.notesapp.ui.components.ConfirmDeleteDialog
import com.csarchvz.notesapp.ui.components.DetailNoteFloatingActionButtons
import com.csarchvz.notesapp.ui.components.DetailNoteTopBar
import com.csarchvz.notesapp.ui.components.ListSelectionDialog
import com.csarchvz.notesapp.ui.components.NoteContent
import com.csarchvz.notesapp.viewModel.NoteViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailNoteScreen(noteId: Int, viewModel: NoteViewModel, navController: NavController) {
    val scope = rememberCoroutineScope()
    val noteState = remember { mutableStateOf(DetailNotePlaceHolder.noteDetailPlaceHolder) }
    val showListDialog = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("") }
    val showDeleteConfirmDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = noteId) {
        scope.launch {
            noteState.value =
                viewModel.getNote(noteId) ?: DetailNotePlaceHolder.noteDetailPlaceHolder
            selectedOption.value = noteState.value.list
        }
    }

    Scaffold(
        topBar = {
            DetailNoteTopBar(navController = navController, noteTitle = noteState.value.title)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            DetailNoteFloatingActionButtons(
                onDeleteClicked = { showDeleteConfirmDialog.value = true },
                onEditClicked = { navController.navigate(NavigationRoutes.noteEditNavigation(noteId)) },
                onSetToListClicked = { showListDialog.value = true }
            )
        }
    ) { innerPadding ->
        NoteContent(modifier = Modifier.padding(innerPadding), note = noteState.value)

        if (showListDialog.value) {
            ListSelectionDialog(
                selectedOption = selectedOption.value,
                onOptionSelected = { option ->
                    selectedOption.value = option
                },
                onDismiss = { showListDialog.value = false },
                onConfirm = {
                    viewModel.updateNote(
                        NoteEntity(
                            id = noteId,
                            title = noteState.value.title,
                            body = noteState.value.body,
                            list = selectedOption.value,
                        )
                    )
                    navController.navigate(NavigationRoutes.NAVIGATION_HOME)
                    showListDialog.value = false
                }
            )
        }

        if (showDeleteConfirmDialog.value) {
            ConfirmDeleteDialog(
                onConfirm = {
                    viewModel.deleteNote(
                        NoteEntity(
                            id = noteId,
                            title = noteState.value.title,
                            body = noteState.value.body,
                            list = noteState.value.list
                        )
                    )
                    showDeleteConfirmDialog.value = false
                    navController.popBackStack()
                },
                onDismiss = { showDeleteConfirmDialog.value = false }
            )
        }
    }
}
