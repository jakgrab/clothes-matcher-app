package com.example.clothesmatcher.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothesmatcher.R
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.widgets.ClothesTopAppBar
import com.example.clothesmatcher.widgets.GradientButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavHostController) {

    val topAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Black,
    )

    Scaffold(
        topBar = {
            ClothesTopAppBar(
                title = "Clothes Matcher",
                colors = topAppBarColors,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(Color.Black),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.design),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Fit
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                GradientText(text = stringResource(R.string.app_description_1))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                GradientText(
                    text = stringResource(R.string.app_description_2),
                    textAlign = TextAlign.End
                )
            }
            val gradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF6178F0),
                    MaterialTheme.colorScheme.inversePrimary,
                    Color(0xFFCB7EF5)
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                GradientButton(
                    text = "Let's Go",
                    gradient = gradient,
                    backgroundColor = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    modifier = Modifier.size(width = 200.dp, height = 60.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 24.dp, height = 24.dp)
                        )
                    },
                    spacerWidth = 5.dp,
                    onClick = {
                        navController.navigate(ClothesScreens.MainScreen.name)
                    }
                )
            }
        }
    }
}


@Composable
@OptIn(ExperimentalTextApi::class)
private fun GradientText(
    text: String,
    textAlign: TextAlign? = TextAlign.Start
) {
    val gradientColors = listOf(
        Color(0xFF4FC3F7),
        Color(0xFF6178F0),
        MaterialTheme.colorScheme.inversePrimary,
        Color(0xFFCB7EF5)
    )
    Text(
        text = text,
        modifier = Modifier.size(width = 250.dp, height = 70.dp),
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        textAlign = textAlign,
        softWrap = true,
        maxLines = 3,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        )
    )
}