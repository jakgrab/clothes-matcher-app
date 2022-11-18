package com.example.clothesmatcher.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clothesmatcher.screens.main.main.MainScreen

@Composable
fun ClothesNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ClothesScreens.MainScreen.name
    ) {
        composable(ClothesScreens.MainScreen.name) {
            MainScreen()
        }
    }
}