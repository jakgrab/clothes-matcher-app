package com.example.clothesmatcher.screens.main.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.clothesmatcher.widgets.GradientButton

@Composable
fun ShowAndSendPhoto(
    imageUri: MutableState<Uri?>,
    buttonGradient: Brush,
    onSendImage: () -> Unit,
    onCancel: () -> Unit
) {

    val cancelButtonGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE936B6),
            Color(0xFFDD0494),
            Color(0xFFBD0E23)
        )
    )

    Surface(
        modifier = Modifier
            .size(width = 270.dp, height = 360.dp)
            .background(MaterialTheme.colorScheme.background)
            .clip(shape = RoundedCornerShape(35.dp)),
    ) {
        AsyncImage(
            model = imageUri.value,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Selected photo"
        )
    }

    Spacer(modifier = Modifier.height(60.dp))

    GradientButton(
        text = "Find Matches",
        gradient = buttonGradient,
        modifier = Modifier.size(width = 200.dp, height = 70.dp),
        onClick = {
            onSendImage()
        }
    )

    Spacer(modifier = Modifier.height(30.dp))

    GradientButton(
        text = "Cancel",
        gradient = cancelButtonGradient,
        modifier = Modifier.size(width = 150.dp, height = 50.dp),
        onClick = {
            onCancel()
        }
    )
}