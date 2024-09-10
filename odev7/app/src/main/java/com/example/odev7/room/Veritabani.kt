package com.example.odev7.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.odev7.entity.Kisiler


@Database(entities = [Kisiler::class], version = 2)
abstract class Veritabani : RoomDatabase() {
    abstract fun KisilerDao() : KisilerDao
 companion object{
     var INSTANCE:Veritabani?=null
     fun veritabaniErisim(context:Context):Veritabani?{
         if(INSTANCE==null){
             synchronized(Veritabani::class){
                 INSTANCE= Room.databaseBuilder(
                     context.applicationContext,
                     Veritabani::class.java,
                     "toDos.sqlite"
                 ).createFromAsset("toDos.sqlite").build()
             }
         }
         return INSTANCE
     }
 }

}