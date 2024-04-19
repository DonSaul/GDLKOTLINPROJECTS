package com.csarchvz.notesapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputFields(
    title: String,
    onTitleChange: (String) -> Unit,
    noteBody: String,
    onNoteBodyChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Title note")
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Title") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                containerColor = Color(0xFFF2F2F2)
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Body note")
        OutlinedTextField(
            value = noteBody,
            onValueChange = onNoteBodyChange,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            label = { Text(text = "Body") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                containerColor = Color(0xFFF2F2F2)
            )
        )
    }
}

@Composable
fun NoteActionButtons(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    saveEnabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Clear, contentDescription = "Cancel") },
            text = { Text("Cancel") },
            onClick = onCancel,
        )
        Spacer(modifier = Modifier.width(16.dp))
        ExtendedFloatingActionButton(
            containerColor = if (saveEnabled) Color.Green else Color.Gray,
            icon = { Icon(Icons.Filled.Check, contentDescription = "Save") },
            text = { Text("Save") },
            onClick = onSave,
        )
    }
}
