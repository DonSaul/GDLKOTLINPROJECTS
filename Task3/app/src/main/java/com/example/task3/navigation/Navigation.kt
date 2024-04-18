package com.example.task3.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task3.screens.AddNoteScreen
import com.example.task3.screens.LoadingScreen
import com.example.task3.screens.MainScreen
import com.example.task3.viewmodels.NoteViewModel


/**
 *  Composoble to manage navigation between screens.
 *
 *  @param[noteViewModel] the ViewModel to handle state of the notes taken.
 * */
@Composable
fun NavManager(noteViewModel: NoteViewModel){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SplashScreen"){
        composable("MainScreen"){
            MainScreen(navController, noteViewModel)
        }
        composable("AddNoteScreen"){
            AddNoteScreen(navController, noteViewModel)
        }
        composable("SplashScreen"){
            LoadingScreen(navController)
        }
    }
}