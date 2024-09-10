package com.example.odev7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.odev7.entity.ToDo
import com.example.odev7.repo.ToDoRepository
import com.example.odev7.room.AppDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoRepository = ToDoRepository(AppDatabase.getDatabase(application).toDoDao())
    val allNotes: LiveData<List<ToDo>> = repository.getAllNotes()  // LiveData olarak alınmalı

    // Notları ID'ye göre al
    fun getNoteById(noteId: Int): LiveData<ToDo> {
        return liveData {
            emit(repository.getNoteById(noteId))
        }
    }

    // Not ekleme veya güncelleme
    fun addOrUpdate(note: ToDo) = viewModelScope.launch {
        repository.addOrUpdate(note)
    }
}
