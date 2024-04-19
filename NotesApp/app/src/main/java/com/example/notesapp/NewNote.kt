package com.example.notesapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ContentAlpha

@Composable
fun NewNote(controlNav: NavHostController) {
    var Notes = notes
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var emptyTitle by remember { mutableStateOf(false) }
    var emptyDescription by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        OutlinedButton(modifier = Modifier.padding(11.dp),
            //We move to a different screen
            onClick = {
                controlNav.navigate("NotesList") {
                    popUpTo("NotesList") { inclusive = true }
                }

            }) {
            Text(text = "<-")
        }
    }
    //Design
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        //Title input
        OutlinedTextField(value = title,
            onValueChange = { title = it
                            emptyTitle = false},
            label = {Text("Title")},
            placeholder = {Text("Starbucks")})

        val assistiveElementTitle = if (emptyTitle) "Error: A title is required" else "*Mandatory" // 4
        val assistiveElementColorTitle = if (emptyTitle) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
        }

        Text(
            text = assistiveElementTitle,
            color = assistiveElementColorTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(Modifier.size(20.dp))

        OutlinedTextField(value = description,
            onValueChange = { description = it
                emptyDescription = false},
            label = {Text("Note")},
            placeholder = {Text("Buy a Starbucks mug")},
            maxLines = 5,
            modifier = Modifier.height(100.dp))

        val assistiveElementNote= if (emptyDescription) "Error: This can't be empty" else "*Mandatory" // 4
        val assistiveElementColorNote = if (emptyDescription) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
        }

        Text(
            text = assistiveElementNote,
            color = assistiveElementColorNote,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
                if(title.isNotBlank() && description.isNotBlank())
                {
                    Toast.makeText(context, "Note added successfully", Toast.LENGTH_SHORT).show()
                    notes.add(Note(title,description))
                    controlNav.navigate("NotesList")
                }
                else if(title.isBlank()) { emptyTitle = true }
                else if(description.isBlank()) { emptyDescription = true }
            }
        ) {
            Text(text = "Add note")

        }

    }
}