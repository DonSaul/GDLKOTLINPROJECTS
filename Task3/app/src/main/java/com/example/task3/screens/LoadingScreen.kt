package com.example.task3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.task3.R
import com.example.task3.components.LottieAsset
import kotlinx.coroutines.delay

/**
 *  Composable that represents the splash screen for this app.
 *  It renders an animation for 2.5 seconds and then navigates to the main screen.
 *
 *  @param[navController] a NavController instance for managing navigation between screens
 * */

@Composable
fun LoadingScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.navigate("MainScreen") {
            popUpTo("SplashScreen") {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        LottieAsset(
            modifier = Modifier
                .width(350.dp)
                .height(350.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(alignment = Alignment.Center), image = R.raw.animation3
        )
    }

}