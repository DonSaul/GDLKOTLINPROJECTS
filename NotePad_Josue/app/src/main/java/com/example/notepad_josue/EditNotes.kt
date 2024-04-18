package com.example.notepad_josue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp

//Note text field, receive a string and return the modified string
@Composable
fun EditNotepad(note:String): String{
    var filledText by remember {
        mutableStateOf(note)
    }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(modifier = Modifier.fillMaxWidth().padding(15.dp),
            value = filledText,
            onValueChange = { filledText = it},
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Justify
            ),
            placeholder = {
                Text(text = "Add your note here...")
            },
            minLines = 15,
        )

    }
    return filledText
}

//Title text field, receive a string and return the modified string
@Composable
fun EditTitle(title: String): String{
    var filledText by remember {
        mutableStateOf(title)
    }
    TextField(modifier = Modifier.fillMaxWidth().padding(15.dp),
        value = filledText,
        onValueChange = { filledText = it},
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Justify
        ),
        placeholder = {
            Text(text = "Title")
        },
        minLines = 1,

    )
    return filledText
}