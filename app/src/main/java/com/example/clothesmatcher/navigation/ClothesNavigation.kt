package com.example.clothesmatcher.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clothesmatcher.screens.main.main.MainScreen
import com.example.clothesmatcher.screens.main.main.MainViewModel

@Composable
fun ClothesNavigation() {

    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(
        navController = navController,
        startDestination = ClothesScreens.MainScreen.name
    ) {

        composable(ClothesScreens.MainScreen.name) {

            MainScreen(mainViewModel, navController)
        }

        composable(ClothesScreens.MatchingScreen.name) {
            //ClothesScreens.MatchingScreen()
        }
    }
}