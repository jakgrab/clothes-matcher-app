package com.example.clothesmatcher.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    ClothesMatcherTheme {

        LaunchedEffect(true) {
            delay(1000)
            navController.navigate(ClothesScreens.LandingScreen.name) {
                popUpTo(ClothesScreens.SplashScreen.name) { inclusive = true }
            }
        }
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {}
            }
        }
    }
}