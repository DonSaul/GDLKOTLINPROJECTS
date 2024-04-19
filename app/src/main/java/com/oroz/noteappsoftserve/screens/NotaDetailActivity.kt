package com.oroz.noteappsoftserve.screens

import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.oroz.noteappsoftserve.MainActivity
import com.oroz.noteappsoftserve.R
import com.oroz.noteappsoftserve.ui.theme.MainTheme

class NotaDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notaDetalle = intent.extras!!.getSerializable("notaDetalle") as? NotaClass
        setContent{
            MainTheme {
                val screenState = remember {
                    MutableTransitionState(false).apply { targetState = true }
                }
                AnimatedVisibility(
                    visibleState = screenState,
                    content = { NotasDetailScreen(notaDetalle = notaDetalle!!) },
                    enter = fadeIn(),
                    exit = fadeOut()
                )
            }
        }
    }
}

@Composable
fun NotasDetailScreen(
    modifier: Modifier = Modifier,
    notaDetalle: NotaClass = NotaClass(titulo = "", contenido = "")
) {
    val context = LocalContext.current
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
            Box(modifier = modifier.fillMaxSize()) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 12.dp, bottom = 24.dp)
                )  {
                    Icon(
                        Icons.Filled.ChevronLeft, "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable(
                                enabled = true,
                                onClickLabel = null,
                                role = Role.Button,
                                onClick = {
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent)
                                }
                            )
                            .background(Color.Transparent, RoundedCornerShape(16.dp)),
                        tint = Color.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                        .padding(bottom = 30.dp)
                ) {
                    TextInputDetail(
                        value = notaDetalle.titulo,
                        modifier = Modifier
                            .fillMaxHeight(0.10F)
                            .fillMaxWidth(),
                        label = "Título",
                        placeholder = ""
                    )
                    TextInputDetail(
                        value = notaDetalle.contenido,
                        modifier = Modifier
                            .fillMaxHeight(0.80F)
                            .fillMaxWidth(),
                        label = "Contenido",
                        placeholder = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NotasDetailPreview() {
    val notaDetalle = NotaClass(titulo = "TITULO PRUEBA", contenido = "CONTENIDO PRUEBA")
    NotasDetailScreen(notaDetalle = notaDetalle)
}

@Composable
fun TextInputDetail(
    value: String,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
) {
    OutlinedTextField(
        value = value,
        label = { Text(label, color = Color.White) },
        modifier = modifier.clickable {  },
        shape = RoundedCornerShape(16.dp),
        onValueChange = {},
        textStyle = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Bold
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            disabledBorderColor = Color.White,
            disabledPlaceholderColor = Color.White,
            disabledTextColor = Color.White,
            disabledLabelColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedPlaceholderColor = Color.White
        ),
        placeholder = { Text(placeholder, color = Color.White) },
        singleLine = false,
        maxLines = 10,
        visualTransformation = VisualTransformation.None
    )
}