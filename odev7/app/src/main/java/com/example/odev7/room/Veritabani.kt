package com.example.odev7.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.odev7.data.entity.Notlar

@Database(entities = [Notlar::class], version = 2)
abstract class Veritabani : RoomDatabase() {
    abstract fun getNotlarDao() : NotlarDao
}