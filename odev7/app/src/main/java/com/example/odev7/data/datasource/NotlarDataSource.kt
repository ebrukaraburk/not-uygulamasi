package com.example.odev7.data.datasource

import com.example.odev7.data.entity.Notlar
import com.example.odev7.room.NotlarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotlarDataSource(var ndao:NotlarDao) {
    suspend fun kaydet(title:String,context:String,date:String){
        val yeniNot = Notlar(0,title,context,date)
        ndao.kaydet(yeniNot)
    }

    suspend fun guncelle(id:Int,title: String,context:String,date:String){
        val guncellenenNot = Notlar(id, title, context,date)
        ndao.guncelle(guncellenenNot)
    }

    suspend fun sil(id:Int){
        val silinenNot = Notlar(id,"","","")
        ndao.sil(silinenNot)
    }

    suspend fun notlariYukle() : List<Notlar> = withContext(Dispatchers.IO){
        return@withContext ndao.notlarYukle()
    }

    suspend fun ara(aramaKelimesi:String) : List<Notlar> = withContext(Dispatchers.IO){
        return@withContext ndao.ara(aramaKelimesi)
    }

}