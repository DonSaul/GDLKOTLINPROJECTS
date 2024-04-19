package com.oroz.noteappsoftserve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.oroz.noteappsoftserve.navigation.SetUpNavGraph
import com.oroz.noteappsoftserve.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent{
            val systemUiController: SystemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.isStatusBarVisible = false
                systemUiController.isNavigationBarVisible = false
            }

            MainTheme {
                val navController = rememberNavController()

                val screenState = remember {
                    MutableTransitionState(false).apply { targetState = true }
                }

                AnimatedVisibility(
                    visibleState = screenState,
                    content = { SetUpNavGraph(navController = navController) },
                    enter = fadeIn(),
                    exit = fadeOut()
                )
            }
        }
        super.onCreate(savedInstanceState)
    }
}