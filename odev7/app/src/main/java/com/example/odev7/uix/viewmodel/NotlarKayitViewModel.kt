package com.example.odev7.uix.viewmodel

import androidx.lifecycle.ViewModel
import com.example.odev7.data.repo.NotlarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotlarKayitViewModel @Inject constructor(var nrepo:NotlarRepository) : ViewModel() {

    fun kaydet(title:String,context:String,date:String){
        CoroutineScope(Dispatchers.Main).launch {
            nrepo.kaydet(title, context,date)
        }
    }
}