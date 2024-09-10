package com.example.odev7.repo

import androidx.lifecycle.LiveData
import com.example.odev7.entity.ToDo
import com.example.odev7.room.ToDoDao

class ToDoRepository(private val toDoDao: ToDoDao) {

    // Belirli bir notu ID'ye göre al
    suspend fun getNoteById(noteId: Int): ToDo {
        return toDoDao.getNoteById(noteId)
    }

    // Not ekleme veya güncelleme işlemi
    suspend fun addOrUpdate(note: ToDo) {
        if (note.id == 0) {
            toDoDao.insert(note)
        } else {
            toDoDao.update(note)
        }
    }

    // Tüm notları al
    fun getAllNotes(): LiveData<List<ToDo>> {
        return toDoDao.getAllNotes() // LiveData olarak döndürülmeli
    }
}
