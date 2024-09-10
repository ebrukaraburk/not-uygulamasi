package com.example.odev7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.odev7.entity.Kisiler


@Dao
interface KisilerDao {//Dao : Database Access Object
@Query("SELECT * FROM toDos")
suspend fun tumKisiler() : List<Kisiler>

    @Insert
   suspend fun kisiEkle(kisi:Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi:Kisiler)

    @Delete
    suspend fun kisiSil(kisi:Kisiler)

    @Query("SELECT * FROM toDos WHERE kisi_ad like '%' || :aramaKelimesi || '%'")
    suspend fun kisiArama(aramaKelimesi:String) : List<Kisiler>
}