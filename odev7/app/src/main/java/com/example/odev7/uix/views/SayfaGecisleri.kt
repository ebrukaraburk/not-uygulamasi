package com.example.odev7.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.odev7.data.entity.Notlar
import com.example.odev7.uix.viewmodel.AnasayfaViewModel
import com.example.odev7.uix.viewmodel.NotlarDetayViewModell
import com.example.odev7.uix.viewmodel.NotlarKayitViewModel
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,
                   notlarKayitViewModel: NotlarKayitViewModel,
                   notlarDetayViewModell: NotlarDetayViewModell){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }
        composable("notKayitSayfa"){
            NotlarKayitSayfa(notlarKayitViewModel)
        }
        composable(
            "notDetaySayfa/{not}",
            arguments = listOf(
                navArgument("not") { type = NavType.StringType }
            )
        ){
            val json = it.arguments?.getString("not")
            val nesne = Gson().fromJson(json, Notlar::class.java)
            NotlarDetaySayfa(nesne,notlarDetayViewModell)
        }
    }
}