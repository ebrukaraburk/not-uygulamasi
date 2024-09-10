package com.example.odev7.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.odev7.entity.Kisiler
import com.example.odev7.repo.KisilerDaoRepository

class AnasayfaViewModel(application: Application):AndroidViewModel(application) {
    var krepo= KisilerDaoRepository(application)
    var kisilerListesi= MutableLiveData<List<Kisiler>>()

    init {
        kisileriYukle()
        kisilerListesi=krepo.kisileriGetir()
    }
fun kisileriYukle(){
   krepo.tumKisilerAl()
 }

    fun ara(aramaKelimesi:String){
krepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisi_id:Int){
krepo.kisiSil(kisi_id)
    }

}