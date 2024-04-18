package com.example.task3.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task3.R


/**
 *  Composable for a Card, to represent a note and its content ([title],
 *  [content], [description] and [date]).
 *
 *  @param[title] a String.
 *  @param[content] a String.
 *  @param[description] a String.
 *  @param[date] a String.
 *  @param[onClick] a function to be called when implemented for possible future
 *                  features
 * */
@Composable
fun CardNote(
    title: String,
    content: String,
    description: String,
    date: String,
    onClick: () -> Unit
) {
    var showAlert by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .size(width = 340.dp, height = 140.dp)
    ) {
        Column(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .clickable { showAlert = true }
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Column {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 24.sp, textDecoration = TextDecoration.Underline)
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(text = description, fontStyle = FontStyle.Italic)

                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date Icon")
                        Text(text = date, color = Color.Gray)
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        onClick()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
                    if (showAlert) {
                        Alert(
                            title = title,
                            content = content,
                            confirmText = "Accept",
                            description = description,
                            onConfirmClick = { showAlert = false }) {

                        }
                    }
                }

            }
        }
    }
    Spacer(modifier = Modifier.size(16.dp))
}

