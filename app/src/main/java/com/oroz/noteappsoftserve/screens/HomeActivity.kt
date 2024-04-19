package com.oroz.noteappsoftserve.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oroz.noteappsoftserve.R
import com.oroz.noteappsoftserve.ui.theme.MainTheme
import com.oroz.noteappsoftserve.utils.Preferencias
import kotlinx.coroutines.delay
import java.io.Serializable

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainTheme {
                val screenState = remember {
                    MutableTransitionState(false).apply { targetState = true }
                }
                AnimatedVisibility(
                    visibleState = screenState,
                    content = { HomeScreen() },
                    enter = fadeIn(),
                    exit = fadeOut()
                )
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val notasList = arrayListOf(
        NotaClass("Nota 1", "Contenido de la nota 1"),
        NotaClass("Nota 2", "Contenido de la nota 2"),
        NotaClass("Nota 3", "Contenido de la nota 3")
    )

    Log.i("debug", Preferencias.obtenerNotas(LocalContext.current).toString())

    notasList.addAll(Preferencias.obtenerNotas(LocalContext.current))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.softserve_background),
                contentDescription = "Login Background",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                List(notas = notasList, context = LocalContext.current)
            }
        }
    }
}

data class NotaClass(
    var titulo: String = "",
    var contenido: String = "",
) : Serializable {
    fun isNotEmpty(): Boolean {
        return  titulo.isNotEmpty() && contenido.isNotEmpty()
    }
}


@Composable
fun List(modifier: Modifier = Modifier, notas: ArrayList<NotaClass>, context : Context) {
    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(notas.size) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = modifier,
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.small)
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier.weight(3F),
                            fontSize = 24.sp,
                            text = "#${it+1} - ${notas[it].titulo}",
                            color = Color.White
                        )
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(138, 201, 38)
                            ),
                            modifier = modifier
                                .weight(1F),
                            shape = RoundedCornerShape(6.dp),
                            onClick = {
                                val intent = Intent(context, NotaDetailActivity::class.java)
                                intent.putExtra("notaDetalle", notas[it])
                                context.startActivity(intent)
                            },
                        ) {
                            Icon(
                                Icons.Filled.NoteAlt,
                                contentDescription = "Form",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}