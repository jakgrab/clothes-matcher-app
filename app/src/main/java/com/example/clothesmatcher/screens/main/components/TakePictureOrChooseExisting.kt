package com.example.clothesmatcher.screens.main.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothesmatcher.PhotoFileProvider
import com.example.clothesmatcher.widgets.GradientButton

@Composable
fun TakePictureOrChooseExisting(
    buttonGradient: Brush,
    imagePicker: ManagedActivityResultLauncher<String, Uri?>,
    context: Context,
    imageUri: MutableState<Uri?>,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>
) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Choose a photo with clothes to match.",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            softWrap = true,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(200.dp))

    GradientButton(
        text = "Select From Gallery",
        gradient = buttonGradient,
        modifier = Modifier.size(width = 300.dp, height = 70.dp),
        onClick = {
            imagePicker.launch("image/*")
        }
    )

    Spacer(modifier = Modifier.height(40.dp))

    GradientButton(
        text = "Take A Photo",
        gradient = buttonGradient,
        modifier = Modifier.size(width = 300.dp, height = 70.dp),
        onClick = {
            val uri = PhotoFileProvider.getImageUri(context)
            imageUri.value = uri
            cameraLauncher.launch(uri)
        }
    )
}