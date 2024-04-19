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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController,

    ) {
    val currentNote = remember { mutableStateOf("") }
    val currentTitle = remember { mutableStateOf("") }
    val saveButtonState = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Create new note") },
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
                        viewModel.insertNote(
                            title = currentTitle.value,
                            body = currentNote.value,
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
                    .padding(10.dp, 10.dp)
            ) {
                Text(text = "Title note")
                OutlinedTextField(
                    value = currentTitle.value,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        containerColor = Color(0xFFF2F2F2)
                    ),
                    onValueChange = { value ->
                        currentTitle.value = value
                        saveButtonState.value =
                            currentTitle.value.isNotEmpty() && currentNote.value.isNotEmpty()
                    },
                    label = { Text(text = "Title") }
                )
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "Body note")
                OutlinedTextField(
                    value = currentNote.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        containerColor = Color(0xFFF2F2F2)
                    ),
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth(),
                    onValueChange = { value ->
                        currentNote.value = value
                        saveButtonState.value =
                            currentTitle.value.isNotEmpty() && currentNote.value.isNotEmpty()
                    },
                    label = { Text(text = "Body") }
                )
            }
        }
    )
}
