package com.example.odev7

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.odev7.ui.screens.NoteDetailScreen
import com.example.odev7.ui.screens.NoteListScreen
import com.example.odev7.ui.theme.Odev7Theme
import com.example.odev7.viewmodel.NoteViewModel

import com.example.odev7.viewmodel.NoteDetailViewModel

import com.example.odev7.viewmodelfactory.NoteDetailViewModelFactory
import com.example.odev7.viewmodelfactory.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Odev7Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "note_list") {
        composable("note_list") {
            val viewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(LocalContext.current.applicationContext as Application))
            NoteListScreen(navController, viewModel)
        }
        composable("note_detail/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt()
            val viewModel: NoteDetailViewModel = viewModel(factory = NoteDetailViewModelFactory(LocalContext.current.applicationContext as Application))
            NoteDetailScreen(noteId, viewModel)
        }
    }
}


