package com.example.notepad_josue.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notepad_josue.EditNotepad
import com.example.notepad_josue.EditTitle
import com.example.notepad_josue.data.DataProv
import com.example.notepad_josue.data.IdNote
import com.example.notepad_josue.data.Note

@Composable
fun NoteScreen(ControlNav: NavHostController,id:Int){
    var note : Note
    var title = ""
    var notes = ""
    if(id >= 0) {
        note = DataProv.noteList.toSet().elementAt(id - 1)
        title = note.title
        notes = note.note
    }
    
    Column (modifier = Modifier.width(900.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically){

            TextButton(
                modifier = Modifier.width(40.dp),
                onClick = {
                    if(title.isEmpty())
                        title="No title"
                    if(notes.isNotEmpty()) {
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
                    ControlNav.navigate("HomeScreen") }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Return")
            }
            title = EditTitle(title = title)
            Divider (
                color = Color.White,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        notes = EditNotepad(note = notes)
        Spacer(modifier = Modifier.height(50.dp))

    }

}