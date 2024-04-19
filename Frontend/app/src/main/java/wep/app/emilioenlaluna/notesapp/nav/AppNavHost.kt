package wep.app.emilioenlaluna.notesapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import wep.app.emilioenlaluna.notesapp.screens.AddNoteScreen
import wep.app.emilioenlaluna.notesapp.screens.DetailNoteScreen
import wep.app.emilioenlaluna.notesapp.screens.EditNoteScreen
import wep.app.emilioenlaluna.notesapp.ui.screens.MainScreen
import wep.app.emilioenlaluna.notesapp.viewmodel.ListViewModel


@Composable
fun AppNavHost(viewModel: ListViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable("add") {
            AddNoteScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            "detail/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            if (noteId != null) {
                DetailNoteScreen(navController = navController, noteId = noteId, viewModel = viewModel)
            }
        }
        composable(
            "edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            if (noteId != null) {
                viewModel.editNote(noteId)
                EditNoteScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}