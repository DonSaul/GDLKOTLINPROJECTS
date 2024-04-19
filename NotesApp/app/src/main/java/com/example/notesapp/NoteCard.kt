package com.example.notesapp

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun NoteCard(Title: String, Description: String, navController: NavHostController) {
    //Design so we can show up the notes in a card
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navController.navigate("NoteDetail/$Title/$Description") {
                    popUpTo("NoteDetail") { inclusive = true }

                }
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //We'll display the title and the note description
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = Title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = Description,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
