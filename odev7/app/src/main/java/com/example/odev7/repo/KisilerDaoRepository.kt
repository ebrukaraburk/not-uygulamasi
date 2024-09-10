package com.example.odev7.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.odev7.entity.Kisiler
import com.example.odev7.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KisilerDaoRepository (var application: Application){
    var kisilerListesi=MutableLiveData<List<Kisiler>>()
    var vt:Veritabani

    init {
        vt=Veritabani.veritabaniErisim(application)!!
        kisilerListesi=MutableLiveData()
    }

    fun kisileriGetir():MutableLiveData<List<Kisiler>>{
        return kisilerListesi
    }

    fun tumKisilerAl(){
     val job:Job=CoroutineScope(Dispatchers.Main).launch {
         kisilerListesi.value=vt.KisilerDao().tumKisiler()
     }
    }
    fun kisiAra(aramaKelimesi:String){
        val job:Job=CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value=vt.KisilerDao().kisiArama(aramaKelimesi)
        }
    }
    suspend fun kisiKayit(kisi_ad:String, kisi_tel:String){
        val job:Job=CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value=vt.KisilerDao().tumKisiler()
        }
        val yeniKisi=Kisiler(0,kisi_ad,kisi_tel)
        vt.KisilerDao().kisiEkle(yeniKisi)
    }
    fun kisiGuncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String){
          CoroutineScope(Dispatchers.IO).launch {
            val guncellenenKisi = Kisiler(kisi_id, kisi_ad, kisi_tel)
            vt.KisilerDao().kisiGuncelle(guncellenenKisi)
            tumKisilerAl() // Verileri güncelle
        }  }


    fun kisiSil(kisi_id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val silinenKisi = Kisiler(kisi_id, "", "")
            vt.KisilerDao().kisiSil(silinenKisi)
            tumKisilerAl() // Verileri güncelle
        }
    }
}