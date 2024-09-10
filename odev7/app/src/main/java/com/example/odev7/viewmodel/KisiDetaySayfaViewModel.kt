package com.example.odev7.viewmodel

import android.app.Application


import androidx.lifecycle.AndroidViewModel

import com.example.odev7.repo.KisilerDaoRepository

class KisiDetaySayfaViewModel (application: Application):AndroidViewModel(application)  {
    var krepo= KisilerDaoRepository(application)



    fun guncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String){
      krepo.kisiGuncelle(kisi_id,kisi_ad,kisi_tel)
    }
}
