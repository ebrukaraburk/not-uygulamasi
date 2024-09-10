package com.example.odev7

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.odev7.viewmodel.AnasayfaViewModel
import com.example.odev7.viewmodel.KisiKayitSayfaViewModel
import com.example.odev7.viewmodelfactory.AnasayfaViewModelFactory
import com.example.odev7.viewmodelfactory.KisiKayitSayfaViewModelFactory
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiKayitSayfa() {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope() // CoroutineScope oluştur

    val context = LocalContext.current
    val viewModel: KisiKayitSayfaViewModel = viewModel(
        factory = KisiKayitSayfaViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kişi Kayıt") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tfKisiAd.value,
                    onValueChange = { tfKisiAd.value = it },
                    label = { Text("Kişi Adı") }
                )
                TextField(
                    value = tfKisiTel.value,
                    onValueChange = { tfKisiTel.value = it },
                    label = { Text("Kişi Telefonu") }
                )
                Button(onClick = {
                    val kisi_ad = tfKisiAd.value
                    val kisi_tel = tfKisiTel.value
                    // Coroutine başlat
                    coroutineScope.launch {
                        viewModel.kayit(kisi_ad = kisi_ad, kisi_tel = kisi_tel)
                        localFocusManager.clearFocus()
                    }
                }, modifier = Modifier.size(250.dp, 50.dp)) {
                    Text(text = "Kaydet")
                }
            }
        }
    )
}
