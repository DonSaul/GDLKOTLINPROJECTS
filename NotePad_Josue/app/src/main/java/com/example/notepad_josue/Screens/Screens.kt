package com.example.notepad_josue.Screens

sealed class Screens(val route: String){
    object HomeScreen: Screens("HomeScreen")
    object NoteScreen: Screens("NoteScreen")

    //To add Arguments for navigation
    fun withArgs(vararg args: Int?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }


}