package com.example.odev7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odev7.entity.ToDo
import com.example.odev7.repo.ToDoRepository
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: ToDoRepository) : ViewModel() {

    suspend fun getNoteById(noteId: Int): ToDo {
        return repository.getNoteById(noteId)
    }

    fun addOrUpdateNote(note: ToDo) {
        viewModelScope.launch {
            repository.addOrUpdate(note)
        }
    }
}
