package com.example.clothesmatcher.screens.main.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.screens.main.main.MainViewModel
import com.example.clothesmatcher.widgets.GradientButton

@Composable
fun ShowAndSendPhoto(
    imageUri: MutableState<Uri?>,
    buttonGradient: Brush,
//    imageString: MutableState<String?>,
//    navController: NavController,
//    mainViewModel: MainViewModel,
    onSendImage: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .size(width = 330.dp, height = 370.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(35.dp)
    ) {
        AsyncImage(
            model = imageUri.value,
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "Selected photo"
        )
    }

    Spacer(modifier = Modifier.height(100.dp))

    GradientButton(
        text = "Find Matches",
        gradient = buttonGradient,
        modifier = Modifier.size(width = 200.dp, height = 70.dp),
        onClick = {
            onSendImage()
//            if (imageString.value != null) {
//                mainViewModel.postImage(imageString.value)
//                navController.navigate(ClothesScreens.LoadingScreen.name)
//            }
        }
    )

    //showPickSelectionButtons.value = false
    //imageString.value = mainViewModel.imageStringFromUri(context, imageUri.value)
}