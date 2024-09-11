package com.example.odev7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.odev7.ui.theme.Odev7Theme
import com.example.odev7.uix.viewmodel.AnasayfaViewModel
import com.example.odev7.uix.viewmodel.NotlarDetayViewModell
import com.example.odev7.uix.viewmodel.NotlarKayitViewModel
import com.example.odev7.uix.views.SayfaGecisleri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    val anasayfaViewModel: AnasayfaViewModel by viewModels()
    val kisiKayitViewModel: NotlarKayitViewModel by viewModels()
    val kisiDetayViewModel:NotlarDetayViewModell by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Odev7Theme {
                SayfaGecisleri(anasayfaViewModel,kisiKayitViewModel,kisiDetayViewModel)

            }
        }
    }
}


