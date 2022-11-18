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
fun ImagePicker(
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onSendImage: () -> Unit = {}
) {
    // TODO change send button to "done"?
    // TODO move viewModel.postImage from ImagePicker to ImagePOster
    // Todo add a button "send" in ImagePoster
    // TODO Clean up!

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

    val showPickSelectionButtons = remember {
        mutableStateOf(true)
    }

    val imageString = remember {
        mutableStateOf<String?>(null)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (hasImage && imageUri != null) {
            AsyncImage(
                model = imageUri,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Selected Image"
            )
            showPickSelectionButtons.value = false
            imageString.value = viewModel.imageStringFromUri(context, imageUri)

        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (showPickSelectionButtons.value) {
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
                    }
                ) {
                    Text(text = "Take Photo")
                }
            } else {
                Button(
                    onClick = {
                        if (imageString.value != null) {
                            onSendImage.invoke()
                            viewModel.postImage(imageString.value)
                        }
                    }
                ) {
                    Text(text = "Send image")
                }

            }

        }
    }
}

@Composable
fun ImageSender(
) {
    //viewModel.postImage(imageString.value)


}