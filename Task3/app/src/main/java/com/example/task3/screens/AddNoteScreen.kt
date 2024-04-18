package com.example.task3.screens

import androidx.compose.runtime.Composable
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.task3.viewmodels.NoteViewModel

/**
 *  Composable to represent the screen where the user creates a new note.
 *
 *  @param[navController] a NavController instance for managing navigation between screens
 *  @param[noteViewModel] the ViewModel to handle state of the notes taken.
 * */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController, noteViewModel: NoteViewModel) {
    val state = noteViewModel.state
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "New Note", fontWeight = FontWeight.ExtraBold) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "" )
                    }
                },
                actions = {
                    IconButton(onClick = {
                       if(state.content.isBlank() || state.title.isBlank() || state.description.isBlank()){
                           Toast.makeText(context,"Please fill all text inputs", Toast.LENGTH_SHORT).show()
                       }else{
                           noteViewModel.addNote()
                           Toast.makeText(context,"Note saved", Toast.LENGTH_SHORT).show()
                           navController.popBackStack()
                       }
                    }) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Confirm Note Icon", Modifier.size(width = 50.dp, height = 50.dp), tint = Color(0xFF006D42))
                    }
                }
            )
        }
    ) {pad->
        Column(modifier = Modifier
            .padding(pad)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = { noteViewModel.onValueChange("title",it) },
                label = { Text(text = "${state.title.length}/24")},
                placeholder = {
                              Text(text = "Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = { noteViewModel.onValueChange("description",it) },
                label = { Text(text = "${state.description.length}/30")},
                placeholder = {
                              Text(text = "Description")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            OutlinedTextField(
                value = state.content,
                onValueChange = { noteViewModel.onValueChange("content",it) },
                label = { Text(text = "Note")},
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

        }
        DisposableEffect(Unit) {
            onDispose {
                noteViewModel.clearState()
            }
        }
    }
}