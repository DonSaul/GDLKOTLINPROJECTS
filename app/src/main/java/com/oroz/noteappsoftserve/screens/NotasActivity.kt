package com.oroz.noteappsoftserve.screens

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.oroz.noteappsoftserve.R
import com.oroz.noteappsoftserve.navigation.Screen
import com.oroz.noteappsoftserve.ui.theme.MainTheme
import com.oroz.noteappsoftserve.utils.Preferencias
import kotlinx.coroutines.delay

@Composable
fun NotasScreen(modifier: Modifier = Modifier, context: Context, navController: NavController) {
    var registerNotas by remember { mutableStateOf(NotaClass()) }

    var placeholder = "Ingresar"
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                        .padding(bottom = 30.dp)
                ) {
                    TextInputField(
                        value = registerNotas.titulo,
                        onChange = { data -> registerNotas = registerNotas.copy(titulo = data) },
                        modifier = Modifier
                            .fillMaxHeight(0.10F)
                            .fillMaxWidth(),
                        label = "Título",
                        placeholder = "$placeholder"
                    )
                    TextInputFieldMultiLine(
                        value = registerNotas.contenido,
                        onChange = { data -> registerNotas = registerNotas.copy(contenido = data) },
                        modifier = Modifier
                            .fillMaxHeight(0.90F)
                            .fillMaxWidth(),
                        label = "Contenido",
                        placeholder = "$placeholder"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ButtonStyle(
                        "Guardar",
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25F)
                    ) {
                        if (registerNotas.isNotEmpty()) {
                            val nota = NotaClass(registerNotas.titulo, registerNotas.contenido)
                            Preferencias.guardarNota(context, nota)
                            navController.popBackStack()
                            navController.navigate(Screen.Home.route)
                        } else {
                            Toast.makeText(context, "Faltan campos por completar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}

/*@Preview
@Composable
fun NotasPreview() {
    NotasScreen(context = LocalContext.current)
}*/

@Composable
fun TextInputField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    icon : ImageVector = Icons.Default.Person
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            icon,
            contentDescription = "",
            tint = Color.White
        )
    }

    OutlinedTextField(
        value = value,
        label = { Text(label, color = Color.White) },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onValueChange = onChange,
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        leadingIcon = leadingIcon,
        placeholder = { Text(placeholder, color = Color.White) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
    )
}

@Composable
fun TextInputFieldMultiLine(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    icon: ImageVector = Icons.Default.Person
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            icon,
            contentDescription = "",
            tint = Color.White
        )
    }

    OutlinedTextField(
        value = value,
        label = { Text(label, color = Color.White) },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onValueChange = onChange,
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
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        leadingIcon = leadingIcon,
        placeholder = { Text(placeholder, color = Color.White) },
        singleLine = false,
        maxLines = 10,
        visualTransformation = VisualTransformation.None
    )
}


@Composable
fun ButtonStyle(label: String, modifier: Modifier, navigate: () -> Unit) {
    val shape = MaterialTheme.shapes.large
    val black = Color.Black
    val color = Color.White
    val textStyle = MaterialTheme.typography.bodyLarge

    val buttonColors = ButtonDefaults.buttonColors(containerColor = color)

    Button(
        shape = shape,
        onClick = navigate,
        colors = buttonColors,
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, style = textStyle.copy(fontSize = 12.sp, color = black))
        }
    }
}