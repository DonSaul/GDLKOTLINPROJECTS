package com.csarchvz.notesapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.data.entities.NoteEntity

@Composable
fun TitleComponent(title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge, // Asume un estilo similar al de un encabezado
            modifier = Modifier.padding(16.dp)
        )
        Divider()
    }
}

@Composable
fun SearchBarComponent() {

    var inputText by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TextField con icono de lupa
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Search a note") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min), // Ajusta el tamaño automáticamente
            shape = shape
        )

        // Espacio entre TextField y botón de menú
        Spacer(modifier = Modifier.width(8.dp))

        // Botón con icono de menú de tres puntos
        Button(
            onClick = { /* ... */ },
            shape = shape
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "More")
        }
    }

    Divider()
}

@Composable
fun FloatingAddButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("CreateNoteScreen") },
        modifier = Modifier.size(56.dp),
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardExample(item: NoteEntity, navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }
    val noteId: Int? = item.id ?: 0
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = {
            navController.navigate(NavigationRoutes.noteDetailNavigation(noteId ?: 0))
        },
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()

        ) {
            // Contenido principal de la Card
            Text(
                text = item.title,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )

            // Icono en la parte superior derecha
            //IconButton(
            //  onClick = { showDialog.value = true },
            //modifier = Modifier.align(Alignment.TopEnd)
            //) {
            //  Icon(
            //    imageVector = Icons.Default.MoreVert, // Icono de propiedades
            //  contentDescription = "Más opciones"
            //)
            //}

            // Verificamos si el diálogo debe ser mostrado
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {
            Text(text = item.body)
        }
    }
}
