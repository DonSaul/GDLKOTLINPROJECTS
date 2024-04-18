package com.example.notepad_josue.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import com.example.notepad_josue.NOteListItem
import com.example.notepad_josue.data.DataProv

@Composable
fun HomeScreen(ControlNav: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "NotePad (No, no es el de windows)")
        Spacer(modifier = Modifier.height(35.dp))
        val notes = remember {DataProv.noteList}
        LazyColumn(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .size(500.dp) ,contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(
                items = notes,
                itemContent = {
                    NOteListItem(ControlNav,note = it)
                }
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
        Button(onClick = {
            //ControlNav.navigate(Screens.NewNote.route)
            ControlNav.navigate(Screens.NoteScreen.withArgs(-1))
        }) {
            Row {
                Icon(Icons.Rounded.Add,contentDescription = "New Note")
                Text(text = "Create Note")
            }
        }
    }
}