package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.entities.NoteEntity
import com.csarchvz.notesapp.ui.components.*
import com.csarchvz.notesapp.viewModel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNotesScreen(viewModel: NoteViewModel, navController: NavController) {
    val noteList by viewModel.notesList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    val filteredNoteList = filterNotes(noteList, inputText)

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingAddButton(navController) },
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            TitleComponent(title = "My Notes")
            SearchBar(inputText) { inputText = it }
            NotesList(filteredNoteList, navController)
        }
    }
}

fun filterNotes(noteList: List<NoteEntity>?, query: String): List<NoteEntity> {
    return noteList?.filter { note ->
        note.title.contains(query, ignoreCase = true) ||
                note.body.contains(query, ignoreCase = true) ||
                note.list.contains(query, ignoreCase = true)
    } ?: emptyList()
}
