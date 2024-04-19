package com.oroz.noteappsoftserve.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oroz.noteappsoftserve.screens.HomeScreen
import com.oroz.noteappsoftserve.screens.NotasScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    var mContext = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()){
        var selectedScreen by remember { mutableStateOf(1) }
        var textNavbar = ""
        when (selectedScreen) {
            1 -> {
                textNavbar = "Home"
            }
            0 -> {
                textNavbar = "Notas"
            }
        }
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0,26,100)),
            title = {
                Text(
                    color = Color.White,
                    modifier = Modifier.weight(2F),
                    text = textNavbar,
                    textAlign = TextAlign.Center
                )
            }
        )
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(modifier = Modifier.fillMaxWidth().weight(1f))
                selectedScreen = 1
            }
            composable(route = Screen.Notas.route) {
                NotasScreen(modifier = Modifier.fillMaxWidth().weight(1f), context = mContext, navController = navController)
                selectedScreen = 0
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            contentAlignment = Alignment.BottomCenter
        ) {
            NavigationBar(
                containerColor = Color(0,26,100, 255)
            ) {
                when (selectedScreen) {
                    1 -> {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    Icons.Filled.ListAlt,
                                    contentDescription = "Form",
                                    tint = Color.White
                                )
                            },
                            selected = false,
                            onClick = {
                                selectedScreen = 0
                                navController.popBackStack()
                                navController.navigate(Screen.Notas.route)
                            }
                        )
                    }
                    0 -> {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Form",
                                    tint = Color.White
                                )
                            },
                            selected = false,
                            onClick = {
                                selectedScreen = 1
                                navController.popBackStack()
                                navController.navigate(Screen.Home.route)
                            }
                        )
                    }
                }
            }
        }
    }
}
