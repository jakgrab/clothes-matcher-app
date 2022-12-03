package com.example.clothesmatcher.screens.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.screens.main.main.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavHostController, viewModel: MainViewModel) {
    val isImageListEmpty = viewModel.isImageListEmpty.collectAsState()
    val isConnectionSuccessful = viewModel.isConnectionSuccessful.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LaunchedEffect(true) {
            delay(10000)
            navController.navigate(ClothesScreens.MainScreen.name)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoadingAnimation()

            if (!isImageListEmpty.value && isConnectionSuccessful.value == true) {
                navController.navigate(ClothesScreens.MatchingScreen.name)
            }
        }
    }
}