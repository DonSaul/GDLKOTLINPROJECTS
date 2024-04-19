package wep.app.emilioenlaluna.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import wep.app.emilioenlaluna.notesapp.nav.AppNavHost
import wep.app.emilioenlaluna.notesapp.screens.AddNoteScreen
import wep.app.emilioenlaluna.notesapp.screens.DetailNoteScreen
import wep.app.emilioenlaluna.notesapp.screens.EditNoteScreen
import wep.app.emilioenlaluna.notesapp.service.NoteService
import wep.app.emilioenlaluna.notesapp.ui.screens.MainScreen
import wep.app.emilioenlaluna.notesapp.ui.theme.NotesAppTheme
import wep.app.emilioenlaluna.notesapp.viewmodel.ListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = ListViewModel(NoteService.instance)
                    AppNavHost(viewModel = viewModel)
                }
            }
        }
    }
}
