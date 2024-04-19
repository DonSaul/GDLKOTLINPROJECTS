package com.oroz.noteappsoftserve.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oroz.noteappsoftserve.R
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.oroz.noteappsoftserve.MainActivity
import com.oroz.noteappsoftserve.ui.theme.MainTheme
import com.oroz.noteappsoftserve.ui.theme.colorPrimary
import com.oroz.noteappsoftserve.ui.theme.colorPrimaryVariant
import com.oroz.noteappsoftserve.ui.theme.lambdaTypography

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainTheme {
                AnimatedSplashScreen()
            }
        }
    }
}

@Composable
fun AnimatedSplashScreen() {
    val context = LocalContext.current

    var startAnimation by remember { mutableStateOf(false) }
    var startAnimation2 by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        ), label = ""
    )

    val alphaAnim2 = animateFloatAsState(
        targetValue = if (startAnimation2) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        startAnimation2 = true
        startAnimation = false
        delay(2000)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    if (startAnimation) {
        Splash2(alpha = alphaAnim.value)
    }

    if (startAnimation2) {
        Splash(alpha = alphaAnim2.value)
    }
}


@Composable
fun Splash(alpha: Float) {
    val listColors = listOf(colorPrimary, colorPrimaryVariant)
    Box(
        modifier = Modifier
            .background(Brush.linearGradient(listColors))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(275.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(16.dp))
                        .alpha(alpha = alpha),
                )
                Text(
                    text = "TASK 3",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.displayLarge,
                    fontFamily = lambdaTypography,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 62.dp)
                        .alpha(alpha = alpha)
                    ,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Splash2(alpha: Float) {
    val listColors = listOf(colorPrimary, colorPrimaryVariant)

    val brightness = -75F
    val colorMatrix = floatArrayOf(
        1f, 0f, 0f, 0f, brightness,
        0f, 1f, 0f, 0f, brightness,
        0f, 0f, 1f, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )
    Box(
        modifier = Modifier
            .background(Brush.linearGradient(listColors))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*Image(
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Login Background",
            modifier = Modifier.matchParentSize()
                .alpha(alpha = alpha)
            ,
            contentScale = ContentScale.Crop
        )*/
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier

                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(275.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(16.dp))
                        .alpha(alpha = alpha),
                )
            }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}