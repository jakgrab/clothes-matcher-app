package com.example.clothesmatcher.screens.loading

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.screens.main.main.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoadingScreen(navController: NavHostController, viewModel: MainViewModel) {
    val isImageListEmpty = viewModel.isImageListEmpty.collectAsState()
    val isConnectionSuccessful = viewModel.isConnectionSuccessful.collectAsState()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LaunchedEffect(isConnectionSuccessful.value) {
                if (isConnectionSuccessful.value == false) {
                    Log.d("tag", "isConnectionSuccesful: ${isConnectionSuccessful.value}")
                    coroutineScope.launch {
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = "An error occurred while trying to connect to server.",
                            actionLabel = "Go back",
                            duration = SnackbarDuration.Long
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {
                                navController.navigate(ClothesScreens.MainScreen.name)
                            }
                            SnackbarResult.ActionPerformed -> {
                                navController.navigate(ClothesScreens.MainScreen.name)
                            }
                        }
                    }
                }
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
}