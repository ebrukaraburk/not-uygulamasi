package com.example.odev7.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.odev7.repo.KisilerDaoRepository

class KisiKayitSayfaViewModel(application: Application): AndroidViewModel(application)  {
    var krepo=KisilerDaoRepository(application)

     suspend fun kayit(kisi_ad:String, kisi_tel:String){
      krepo.kisiKayit(kisi_ad,kisi_tel)

    }

}