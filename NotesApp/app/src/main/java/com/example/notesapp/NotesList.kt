package com.example.notesapp

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesapp.notes
import com.example.notesapp.Note

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesList(controlNav: NavHostController, noteList: List<Note>) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),

        )
        {
            //We create the item we'll be displaying
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "List of notes: ",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            //We fullfill with the total items in the list
            items(noteList.size) {text ->
                NoteCard(notes[text].Title, notes[text].Description, controlNav)
            }
        }
        //Design
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End) {
            Spacer(modifier = Modifier.height(65.dp))
            OutlinedButton(modifier = Modifier.padding(11.dp),
                //We move to a different screen
                onClick = {
                controlNav.navigate("NewNote") {
                    popUpTo("NewNote") { inclusive = true }
                }

            }) {
                Text(text = "+")
            }
        }
    }
}
/*fun NotesList(controlNav: NavHostController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Welcome ! ", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {
            controlNav.navigate("NewNote"){
                popUpTo("NewNote"){inclusive= true}
            }

        }) {
            Text(text = "Move to a new note")
        }
    }
}*/