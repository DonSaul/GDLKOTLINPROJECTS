package com.oroz.noteappsoftserve.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home_screen")
    object Notas : Screen("permits_screen")
}