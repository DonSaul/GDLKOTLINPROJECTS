package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Navigation()
{
    //Helps us to move to a different screen
    val controlNav = rememberNavController()
    NavHost(navController = controlNav, startDestination = "NotesList"){
        composable(route="NotesList"){
            NotesList(controlNav, notes)
        }
        composable(route="NewNote"){
            NewNote(controlNav)
        }
        composable(route="NoteDetail/{title}/{description}",
            arguments = listOf(navArgument(name="title"){
                type = NavType.StringType
            },
                navArgument(name="description"){
                    type = NavType.StringType
                }
            )){
            backstackEntry ->
            backstackEntry.arguments!!.getString("title")?.let {
                backstackEntry.arguments!!.getString("description")?.let { it1 ->
                    NoteDetail(controlNav, noteTitle = it,
                        noteDescription = it1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewComposable(){

    Navigation()

}