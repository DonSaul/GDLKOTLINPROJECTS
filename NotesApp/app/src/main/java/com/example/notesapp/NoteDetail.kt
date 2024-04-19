package com.example.notesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NoteDetail(ControlNav: NavHostController, noteTitle: String, noteDescription: String) {

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        NoteCard(noteTitle, noteDescription, ControlNav)

        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {

            ControlNav.navigate("NotesList")
            {
                popUpTo("NotesList"){inclusive= true}

            }
        }) {
            Text(text = "Home")

        }

    }


}