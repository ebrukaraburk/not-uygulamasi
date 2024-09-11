package com.example.odev7.data.repo

import com.example.odev7.data.datasource.NotlarDataSource
import com.example.odev7.data.entity.Notlar

class NotlarRepository(var nds:NotlarDataSource) {

    suspend fun kaydet(title:String,context:String,date:String) = nds.kaydet(title, context,date)

    suspend fun guncelle(id:Int,title:String,context:String,date: String) = nds.guncelle(id, title, context,date)

    suspend fun sil(id:Int) = nds.sil(id)

    suspend fun notlariYukle() : List<Notlar> = nds.notlariYukle()

    suspend fun ara(aramaKelimesi:String) : List<Notlar> = nds.ara(aramaKelimesi)
}