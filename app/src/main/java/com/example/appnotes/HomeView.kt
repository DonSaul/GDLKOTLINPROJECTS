package com.example.appnotes

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appnotes.data.DummyNote
import com.example.appnotes.data.Note

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: NoteViewModel
){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(
                title = "My Notes",
                {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = colorResource(id = R.color.backgroundSwipe),
                onClick = {
                    //Toast.makeText(context, "Floating Button Clicked", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route + "/0L")

                })
            {
              Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        val noteList = viewModel.getAllNotes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
             items(noteList.value, key={note -> note.id} ) {
                 note ->
                 val dismissState = rememberDismissState(
                     confirmStateChange = {
                         if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                             viewModel.deleteNote(note)
                         }
                         true
                     }
                 )
                 SwipeToDismiss(state = dismissState,
                     background = {
                              val color by animateColorAsState(
                                  if(dismissState.dismissDirection == DismissDirection.EndToStart) colorResource(id = R.color.backgroundSwipe) else Color.Transparent,
                                  label = ""
                              )
                            val alignment = Alignment.CenterEnd
                         Box(
                             Modifier
                                 .fillMaxSize()
                                 .background(color)
                                 .padding(horizontal = 20.dp),
                             contentAlignment = alignment
                         ){
                             Icon(Icons.Default.Delete, contentDescription = "Delete icon", tint= Color.White )
                         }
                     },
                     directions = setOf(DismissDirection.EndToStart),
                     dismissThresholds = { FractionalThreshold(1f) },
                     dismissContent = {
                         NoteItem(note = note ) {
                             val id = note.id
                             navController.navigate(Screen.AddScreen.route + "/$id")
                         }
                     }

                 )

             }

            }
        }
    }

@Composable
fun NoteItem(note: Note, onClick:() -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        .clickable {
            onClick()

        },
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.notebox)
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(text = note.title, fontWeight = FontWeight.ExtraBold )
            Text(text = note.description)
        }
    }
}