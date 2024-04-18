package com.example.notepad_josue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EditNotepad(note:String): String{
    var filledText by remember {
        mutableStateOf(note)
    }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = filledText,
            onValueChange = { filledText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Justify
            ),
            placeholder = {
                Text(text = "Add your note here...")
            },
            minLines = 20,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent)
        )

    }
    return filledText
}

@Composable
fun EditTitle(title: String): String{
    var filledText by remember {
        mutableStateOf(title)
    }
    TextField(modifier = Modifier.fillMaxWidth(),
        value = filledText,
        onValueChange = { filledText = it},
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Justify
        ),
        placeholder = {
            Text(text = "Title")
        },
        minLines = 1,
        colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent)

    )
    return filledText
}