package com.example.odev7.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDos")
data class ToDo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val date: String
)
