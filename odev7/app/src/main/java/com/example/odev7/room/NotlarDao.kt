package com.example.odev7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.odev7.data.entity.Notlar

@Dao
interface NotlarDao {//Dao : Database Access Object
@Query("SELECT * FROM toDos")
suspend fun notlarYukle() : List<Notlar>

    @Insert
    suspend fun kaydet(notlar:Notlar)

    @Update
    suspend fun guncelle(notlar: Notlar)

    @Delete
    suspend fun sil(notlar:Notlar)

    @Query("SELECT * FROM toDos WHERE title like '%' || :aramaKelimesi || '%'")
    suspend fun ara(aramaKelimesi:String) : List<Notlar>
}