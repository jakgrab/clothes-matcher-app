package com.example.clothesmatcher.screens.match

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothesmatcher.R
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
// viewModel: MainViewModel
fun MatchingScreen() {

    val scaffoldState = rememberScaffoldState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = scaffoldState,
        topBar = {
            ClothesTopAppBar(
                title = "Matches",
                icon = Icons.Rounded.ArrowBackIos,
                actionIcon = Icons.Rounded.Settings,
                onNavigationIconClicked = {

                },
                onActionIconClicked = {

                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)

                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF262331)//0xff313247)
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Here's what the AI has found...",
                        color = Color.White,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Left
                    )
                    Surface(
                        modifier = Modifier
                            .size(width = 330.dp, height = 370.dp),
                        shape = RoundedCornerShape(35.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.drainnn),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    //Image(bitmap = ImageBitmap.imageResource(id = ), contentDescription = )
                }
            }

        }
    }
}