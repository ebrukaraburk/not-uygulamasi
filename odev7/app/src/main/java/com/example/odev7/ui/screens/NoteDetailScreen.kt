package com.example.odev7.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.odev7.entity.ToDo
import com.example.odev7.viewmodel.NoteDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(noteId: Int?, viewModel: NoteDetailViewModel) {
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }

    // Not verilerini al
    LaunchedEffect(noteId) {
        if (noteId != null) {
            // Mevcut not verilerini al
            val note = viewModel.getNoteById(noteId) // getNoteById metodu ViewModel'de tanımlı olmalı
            title.value = note.title
            content.value = note.content
            date.value = note.date
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Not Detayı") })
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Başlık") }
                )
                TextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    label = { Text("İçerik") }
                )
                // Tarih seçici eklenebilir
                Button(onClick = {
                    val toDo = ToDo(
                        id = noteId ?: 0,
                        title = title.value,
                        content = content.value,
                        date = date.value
                    )
                    viewModel.addOrUpdateNote(toDo)
                }) {
                    Text("Kaydet")
                }
            }
        }
    )
}
