package com.example.notepad_josue

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notepad_josue.Screens.HomeScreen
import com.example.notepad_josue.Screens.NoteScreen
import com.example.notepad_josue.Screens.Screens

@Composable
fun Navigator(){
    val ControlNav = rememberNavController()
    NavHost(navController = ControlNav, startDestination = Screens.HomeScreen.route){
        composable(route=Screens.HomeScreen.route){
            HomeScreen(ControlNav)
        }
        composable(
            route=Screens.NoteScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) {entry ->
            entry.arguments?.let { NoteScreen(ControlNav, it.getInt("id")) }
        }
    }
}