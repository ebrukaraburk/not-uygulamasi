package com.example.odev7.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.odev7.entity.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM toDos")
    fun getAllNotes(): LiveData<List<ToDo>>  // Burada LiveData döndürmelisiniz

    @Query("SELECT * FROM toDos WHERE id = :id")
    suspend fun getNoteById(id: Int): ToDo

    @Insert
    suspend fun insert(note: ToDo)

    @Update
    suspend fun update(note: ToDo)

    @Delete
    suspend fun delete(note: ToDo)
}
