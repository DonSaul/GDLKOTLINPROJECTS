package com.example.notepad_josue

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.notepad_josue.data.Note
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notepad_josue.Screens.Screens

@Composable
fun NOteListItem(ControlNav: NavHostController,note: Note){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),

    ) {
        Row(
            Modifier.clickable { ControlNav.navigate(Screens.NoteScreen.withArgs(note.id))  }.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Icon(Icons.Rounded.Edit,contentDescription = "View Note")
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = note.title)
                Text(text = note.note, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp)
            }
        }
    }

}

