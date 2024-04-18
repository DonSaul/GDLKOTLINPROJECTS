package com.example.task3.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *  Composable for an alert, to display details of a Note(its title,
 *  description and content).
 *
 *  @param[title] a String.
 *  @param[description] a String.
 *  @param[content] a String.
 *  @param[confirmText] a String.
 *  @param[onConfirmClick] a function.
 *  @param[onDismissClick] a function.
 * */
@Composable
fun Alert(
    title: String,
    description:String,
    content: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val scroll = rememberScrollState(0)
    AlertDialog(onDismissRequest = { onDismissClick() }, title = { Text(text = title, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center) }, text = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = description)
            Divider()
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = content,
                    textAlign = TextAlign.Justify,
                    modifier =  Modifier
                        .verticalScroll(scroll)
                        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                )
            }
        }
    }, confirmButton = {
        Button(onClick = { onConfirmClick() }) {
            Text(text = confirmText)
        }
    })
}