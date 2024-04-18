package com.csarchvz.notesapp.screens


import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController,

    ) {
    val currentNote = remember { mutableStateOf("") }
    val currentTitle = remember { mutableStateOf("") }
    val saveButtonState = remember { mutableStateOf(false) }
    val isFabExpanded =
        remember { mutableStateOf(false) } // Estado para manejar la expansión de los FABs

    Scaffold(
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
        content = {
            Column(
                Modifier
                    .padding(12.dp)
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
