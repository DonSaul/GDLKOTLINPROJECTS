package com.example.task3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.task3.R
import com.example.task3.components.CardNote
import com.example.task3.viewmodels.NoteViewModel

/**
 *  Composable to represent the screen where the user visualizes its notes.
 *
 *  @param[navController] a NavController instance for managing navigation between screens.
 *  @param[noteViewModel] the ViewModel to handle state of the notes taken.
 * */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, noteViewModel: NoteViewModel) {
    val notes = noteViewModel.notes
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "My notes", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp
            )
        }, actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = "Profile icon"
                )
            }
        }, navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu, contentDescription = "Menu icon"
                )
            }
        })

    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate("AddNoteScreen")
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }

    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            if (notes.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "What's on your mind?",
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic,
                        fontSize = 32.sp,
                        fontFamily = FontFamily.Cursive
                    )
                    Image(
                        painter = painterResource(id = R.drawable.notes2),
                        contentDescription = "",
                        modifier = Modifier
                            .width(360.dp)
                            .height(360.dp)
                    )
                    Text(
                        text = "Add some notes to get started",
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    )
                }
            } else {

                Text(
                    text = "${notes.size} notes",
                    color = Color(0xFFCCC2DC),
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.size(16.dp))
                LazyColumn {
                    items(notes) { item ->
                        CardNote(
                            title = item.title,
                            content = item.content,
                            description = item.description,
                            date = noteViewModel.formatDate(item.createdAt)
                        ) {}
                    }
                }

            }
        }
    }
}
