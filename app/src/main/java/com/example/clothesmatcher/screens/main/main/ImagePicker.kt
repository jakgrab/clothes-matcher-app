package com.example.clothesmatcher.screens.main.main

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import coil.compose.AsyncImage
import com.example.clothesmatcher.PhotoFileProvider
import retrofit2.Response

@Composable
fun ImagePicker (viewModel: MainViewModel = hiltViewModel()) {

    var hasImage by remember {
        mutableStateOf(false)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                // TODO viewModel caused problems
//                if (uri != null) {
//                    val file = uri.path?.let { File(it) }
//                }

                //viewModel.postImage(uri)

                hasImage = uri != null
                imageUri = uri
            }
        )

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                hasImage = success
            })

    val context = LocalContext.current

//    val responseState by remember {
//        mutableStateOf<Response<Int>?>(null)
//    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasImage && imageUri != null) {
            AsyncImage(
                model = imageUri,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Selected Image"
            )
//            Log.d("PIC", "Image URI: $imageUri")
            viewModel.postImage(context, imageUri)

        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }
            ) {
                Text(text = "Select Image")
            }

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    val uri = PhotoFileProvider.getImageUri(context)
                    // TODO viewmodel after cameraLauncher
                    // viewModel causes problems!!!!

                    imageUri = uri
                    cameraLauncher.launch(uri)
                    //viewModel.postImage(uri)
                }
            ) {
                Text(text = "Take Photo")
            }
        }
    }
}