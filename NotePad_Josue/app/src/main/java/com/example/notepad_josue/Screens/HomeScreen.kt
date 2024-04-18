package com.example.notepad_josue.Screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.notepad_josue.NOteListItem
import com.example.notepad_josue.data.DataProv
import com.example.notepad_josue.data.Note
import com.example.notepad_josue.data.NoteDB
import com.example.notepad_josue.data.NoteDao

@Composable
fun HomeScreen(ControlNav: NavHostController){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Notepad", modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //-1 is required to simulate a new note. This should be replaced applying a proper database
                ControlNav.navigate(Screens.NoteScreen.withArgs(-1))
            },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")

            }
        }
    ) {paddingValues ->
        //notes is the simulated DB used for lazycolumn. Should be replaced with proper database
        val notes = remember {DataProv.noteList}
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            ,contentPadding = paddingValues,
            ) {
            items(
                items = notes,
                itemContent = {
                    NOteListItem(ControlNav,note = it)
                }
            )
        }


    }
}