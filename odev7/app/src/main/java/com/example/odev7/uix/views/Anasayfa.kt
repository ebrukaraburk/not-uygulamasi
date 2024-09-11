package com.example.odev7.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.odev7.R
import com.example.odev7.uix.viewmodel.AnasayfaViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController, anasayfaViewModel: AnasayfaViewModel){
    val aramaYapiliyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val notlarListesi = anasayfaViewModel.notlarListesi.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        anasayfaViewModel.notlariYukle()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyorMu.value){
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                anasayfaViewModel.ara(it)
                            },
                            label = { Text(text = "Ara") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.LightGray,  // Arka plan rengi
                                focusedLabelColor = Color.Green,   // Etiket rengi (focuslandığında)
                                focusedIndicatorColor = Color.Green, // Alt çizgi rengi (focuslandığında)
                                unfocusedLabelColor = Color.Black,  // Etiket rengi (focus değilken)
                                unfocusedIndicatorColor = Color.Gray // Alt çizgi rengi (focus değilken)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    } else {
                        Text(text = "Notlar", color = Color.Blue) // Başlık rengi
                    }
                },
               // Üst barın arka plan rengi
                actions = {
                    if (aramaYapiliyorMu.value){
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = false
                            tf.value = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription = "",
                                tint = Color.White // İkon rengi
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.arama_resim),
                                contentDescription = "",
                                tint = Color.Black // İkon rengi
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("notKayitSayfa") },
                containerColor = Color(0xFF1E88E5), // Butonun arka plan rengi
                content = {
                    Icon(painter = painterResource(id = R.drawable.ekle_resim), contentDescription = "", tint = Color.White) // İkonun rengi
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            items(
                count = notlarListesi.value.count(),
                itemContent = {//0,1,2
                    val not = notlarListesi.value[it]
                    Card(modifier = Modifier.padding(all = 30.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val notJson = Gson().toJson(not)
                                    navController.navigate("notDetaySayfa/$notJson")
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.padding(all = 10.dp)) {
                                Text(text = not.title, fontSize = 30.sp, color = Color.Black) // Başlığın rengi
                                Spacer(modifier = Modifier.size(30.dp))
                                Text(text = not.context, color = Color.Black) // Notun içeriğinin rengi
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    val sb = snackbarHostState.showSnackbar(
                                        message = "${not.title} silinsin mi?",
                                        actionLabel = "EVET"
                                    )

                                    if(sb == SnackbarResult.ActionPerformed){
                                        anasayfaViewModel.sil(not.id)
                                    }
                                }
                            }) {
                                Icon(painter = painterResource(id = R.drawable.kapat_resim),
                                    contentDescription = "",
                                    tint = Color.Red // Silme ikonunun rengi
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
