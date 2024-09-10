package com.example.odev7

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.odev7.entity.Kisiler
import com.example.odev7.ui.theme.Odev7Theme
import com.example.odev7.viewmodel.AnasayfaViewModel
import com.example.odev7.viewmodel.KisiDetaySayfaViewModel
import com.example.odev7.viewmodelfactory.AnasayfaViewModelFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Odev7Theme {
                SayfaGecisleri()
            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController= rememberNavController()
    NavHost(navController =navController , startDestination = "anasayfa" ){
        composable("anasayfa"){
            Anasayfa(navController = navController)
        }
        composable("kisi_kayit_sayfa"){
           KisiKayitSayfa()
        }
        composable("kisi_detay_sayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){type= NavType.StringType}
        )){
            val json=it.arguments?.getString("kisi")
            val nesne= Gson().fromJson(json,Kisiler::class.java)
            KisiDetaySayfa(nesne)
        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val aramaYapiliyormu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val context= LocalContext.current
    val viewModel: AnasayfaViewModel = viewModel(
        factory = AnasayfaViewModelFactory(context.applicationContext as Application)
    )
    val kisilerListesi by viewModel.kisilerListesi.observeAsState(emptyList())
    LaunchedEffect (key1 =true ){
        viewModel.kisileriYukle()
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyormu.value) {
                        TextField(
                            value = tf.value,
                            onValueChange = { it ->
                                tf.value = it

                                viewModel.ara(it)


                            },
                            label = { Text("Ara") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.Black
                            )
                        )
                    } else {
                        Text(text = "Kişiler")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (aramaYapiliyormu.value) {
                            aramaYapiliyormu.value = false
                            tf.value = ""
                        } else {
                            aramaYapiliyormu.value = true
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = if (aramaYapiliyormu.value) R.drawable.kapat_resim else R.drawable.arama_resim),
                            contentDescription = "",
                            tint = Color.Green
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 8.dp, // Add some extra padding if needed
                    bottom = paddingValues.calculateBottomPadding()
                )
            ) {
                items(kisilerListesi) { kisi ->
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.clickable {
                            val kisiJson = Gson().toJson(kisi)
                            navController.navigate("kisi_detay_sayfa/${kisiJson}")
                        }) {
                            Row(
                                modifier = Modifier
                                    .padding(all = 20.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "${kisi.kisi_ad} - ${kisi.kisi_tel}")

                                IconButton(onClick = {
                                    viewModel.sil(kisi.kisi_id)

                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.sil_resim),
                                        contentDescription = "",
                                        tint = Color.Blue
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("kisi_kayit_sayfa")
                    Log.e("Button", "ilham verildi")
                },
                containerColor = Color.Red,
                contentColor = Color.Blue,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "",
                        tint = Color.Green
                    )
                }
            )
        }
    )
}

@Composable
fun GreetingPreview() {
    Odev7Theme {

    }
}