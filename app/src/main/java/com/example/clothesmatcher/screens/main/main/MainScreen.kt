package com.example.clothesmatcher.screens.main.main

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clothesmatcher.screens.main.components.ShowAndSendPhoto
import com.example.clothesmatcher.screens.main.components.TakePictureOrChooseExisting
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel, navController: NavController) {
    ClothesMatcherTheme {

        val scaffoldState = rememberScaffoldState()

        val hasImage = remember {
            mutableStateOf(false)
        }

        val imageUri = remember {
            mutableStateOf<Uri?>(null)
        }

        val imagePicker =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri ->
                    hasImage.value = uri != null
                    imageUri.value = uri
                }
            )

        val cameraLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { success ->
                    hasImage.value = success
                }
            )

        val context = LocalContext.current

        val showPickSelectionButtons = remember {
            mutableStateOf(true)
        }

        val imageString = remember {
            mutableStateOf<String?>(null)
        }

        val buttonGradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF6178F0),
                MaterialTheme.colorScheme.inversePrimary,
                Color(0xFFCB7EF5)
            )
        )

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ClothesTopAppBar(
                    title = "Main Screen",
                    actionIcon = Icons.Rounded.Settings,
                    colors = null,
                    onActionIconClicked = {

                    },
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (hasImage.value && imageUri.value != null) {
                    ShowAndSendPhoto(
                        imageUri,
                        buttonGradient,
                        imageString,
                        navController,
                        showPickSelectionButtons,
                        mainViewModel,
                        context
                    )
                }

                if (showPickSelectionButtons.value) {
                    TakePictureOrChooseExisting(
                        buttonGradient,
                        imagePicker,
                        context,
                        imageUri,
                        cameraLauncher
                    )
                }
            }
        }
    }
}





