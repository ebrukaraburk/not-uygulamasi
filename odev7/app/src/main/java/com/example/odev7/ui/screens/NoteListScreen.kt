package com.example.odev7.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import com.example.odev7.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(viewModel: NoteViewModel) {
    // LiveData verilerini @Composable içinde gözlemleyin
    val notes by viewModel.allNotes.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notlar") })
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(notes) { note ->
                    ListItem(
                        modifier = Modifier.clickable {
                            // Navigation işlemleri burada yapılabilir
                        },
                        headlineContent = { Text(text = note.title) } // Güncellenmiş parametre ismi
                    )
                }
            }
        }
    )
}
