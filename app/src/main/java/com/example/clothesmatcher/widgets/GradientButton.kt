package com.example.clothesmatcher.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    spacerWidth: Dp = 0.dp,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(30.dp) // TESTING
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient, shape = RoundedCornerShape(30.dp))
                .then(modifier), // new
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .weight(1f, false)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(spacerWidth))
                icon()
            }
        }
    }
}