package com.example.notepad_josue.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notepad_josue.EditNotepad
import com.example.notepad_josue.EditTitle
import com.example.notepad_josue.data.DataProv
import com.example.notepad_josue.data.IdNote
import com.example.notepad_josue.data.Note

@Composable
fun NoteScreen(ControlNav: NavHostController,id:Int){
    //Initializing variables needed for saving or dropping the note
    val note : Note
    var title = ""
    var notes = ""

    //This takes the note(if exists) previously saved and assign the data
    if(id >= 0) {
        note = DataProv.noteList.toSet().elementAt(id)
        title = note.title
        notes = note.note
    }
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

                if(title.isEmpty())
                    title="No title"

                //Note is created if there is a note(no title required)
                if(notes.isNotEmpty()) {
                    //Simulating the upsert(insert/update), if id < 0 then it's a new note, else it's updated
                    if(id < 0){
                        DataProv.noteList.add(
                            Note(
                                id = IdNote.idNote,
                                title = title,
                                note = notes
                            )
                        )
                        IdNote.idNote += 1
                    } else {
                        DataProv.noteList.find { it.id == id }?.note = notes
                        DataProv.noteList.find { it.id == id }?.title = title
                    }
                }
                ControlNav.navigate("HomeScreen")
            },
                shape = CircleShape) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = "")

            }
        }
    ) {paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)
            .fillMaxSize()
        ) {
            //Composables for title and note (text fields in EditNotes.kt file)
            title = EditTitle(title = title)
            notes = EditNotepad(note = notes)

        }

    }
}