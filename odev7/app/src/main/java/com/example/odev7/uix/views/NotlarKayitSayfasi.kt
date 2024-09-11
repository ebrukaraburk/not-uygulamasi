package com.example.odev7.uix.views

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.odev7.uix.viewmodel.NotlarKayitViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotlarKayitSayfa(notlarKayitViewModel: NotlarKayitViewModel){
    val tfNotBaslik = remember { mutableStateOf("") }
    val tfNot = remember { mutableStateOf("") }
    val tfDate = remember { mutableStateOf("") }


    Scaffold(topBar = { TopAppBar(title = { Text(text = "Not Kayıt") }) }) { paddingValues ->
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
                label = { Text(text = "Notlar") }
            )
            TextField(
                value = tfDate.value,
                onValueChange = { tfDate.value = it },
                label = { Text(text = "Tarihler") }
            )


            Button(
                modifier = Modifier.size(250.dp,50.dp),
                onClick = {
                    notlarKayitViewModel.kaydet(tfNotBaslik.value,tfNot.value,tfDate.value)
                }) { Text(text = "KAYDET") }
        }
    }
}