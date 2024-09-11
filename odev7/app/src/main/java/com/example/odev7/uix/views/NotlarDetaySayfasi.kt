package com.example.odev7.uix.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.odev7.data.entity.Notlar
import com.example.odev7.uix.viewmodel.NotlarDetayViewModell

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotlarDetaySayfa(gelenNot: Notlar, notlarDetaySayfaViewModel: NotlarDetayViewModell){
    val tfNotBaslik = remember { mutableStateOf("") }
    val tfNot= remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        tfNotBaslik.value = gelenNot.title
        tfNot.value = gelenNot.context
    }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Not Detay") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                value = tfNotBaslik.value,
                onValueChange = { tfNotBaslik.value = it },
                label = { Text(text = "Not Baslik") }
            )

            TextField(
                value = tfNot.value,
                onValueChange = { tfNot.value = it },
                label = { Text(text = "Not") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Notu geniş bir alanda göstermek için yüksekliği artırıyoruz
                    .padding(16.dp),
                maxLines = 10, // Notun geniş alanda görünmesi için maxLines ayarı
                minLines = 5 // MinLines ile kutunun ilk baştaki boyutunu ayarlıyoruz
            )

            Button(
                modifier = Modifier.size(250.dp,50.dp),
                onClick = {
                    notlarDetaySayfaViewModel.guncelle(gelenNot.id,tfNotBaslik.value,tfNot.value,gelenNot.date)
                }) { Text(text = "GÜNCELLE") }
        }
    }
}