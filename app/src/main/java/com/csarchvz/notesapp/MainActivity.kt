package com.csarchvz.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.csarchvz.notesapp.data.constants.NavigationRoutes
import com.csarchvz.notesapp.screens.CreateNoteScreen
import com.csarchvz.notesapp.screens.DetailNoteScreen
import com.csarchvz.notesapp.screens.EditNoteScreen
import com.csarchvz.notesapp.screens.HomeNotesScreen
import com.csarchvz.notesapp.ui.theme.NotesAppTheme
import com.csarchvz.notesapp.viewModel.NoteViewModel

class MainActivity : ComponentActivity() {

    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.NAVIGATION_HOME
                    ) {

                        // -- Notes List ---

                        composable(NavigationRoutes.NAVIGATION_HOME) {
                            HomeNotesScreen(viewModel = noteViewModel, navController)
                        }

                        // -- Check Detail Note ---
                        composable(
                            route = NavigationRoutes.NAVIGATION_DETAIL_NOTE_ROUTE,
                            arguments = listOf(navArgument(NavigationRoutes.NOTE_ARGUMENT) {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val noteId =
                                backStackEntry.arguments?.getInt(NavigationRoutes.NOTE_ARGUMENT)
                            if (noteId != null) {
                                DetailNoteScreen(
                                    noteId = noteId,
                                    viewModel = noteViewModel,
                                    navController = navController
                                )
                            }
                        }


                        // -- Edit  Note ---
                        composable(
                            route = NavigationRoutes.NAVIGATION_EDIT_NOTE_ROUTE,
                            arguments = listOf(navArgument(NavigationRoutes.NOTE_ARGUMENT) {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val noteId =
                                backStackEntry.arguments?.getInt(NavigationRoutes.NOTE_ARGUMENT)
                            if (noteId != null) {
                                EditNoteScreen(
                                    noteId = noteId,
                                    viewModel = noteViewModel,
                                    navController = navController
                                )
                            }
                        }
                        // -- Create Note ---
                        composable(NavigationRoutes.NAVIGATION_CREATE_NOTE) {
                            CreateNoteScreen(
                                viewModel = noteViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

