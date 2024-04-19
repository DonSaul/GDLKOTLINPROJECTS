package com.csarchvz.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.entities.NoteEntity
import com.csarchvz.notesapp.ui.components.*
import com.csarchvz.notesapp.viewModel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNotesScreen(viewModel: NoteViewModel, navController: NavController) {
    val noteList by viewModel.notesList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingAddButton(navController) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            TopApp()
            noteList?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(it) { _, item ->
                        CardExample(item, navController)
                    }
                }
            } ?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun TopApp() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TitleComponent(title = "My Notes")
        SearchBarComponent()
    }
}
