package com.example.clothesmatcher.screens.match

import android.graphics.Bitmap
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.screens.main.main.MainViewModel
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchingScreen(navController: NavController, viewModel: MainViewModel) {
    ClothesMatcherTheme {

        val imagesFromServer = viewModel.responseImageState.collectAsState()


        //val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() now params
        // Scaffold( modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection) or other
        // pass scroll behavior to top bar et voilà !

        val scaffoldState = rememberScaffoldState()
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            scaffoldState = scaffoldState,
            topBar = {
                ClothesTopAppBar(
                    title = "Matches",
                    icon = Icons.Rounded.ArrowBackIos,
                    actionIcon = Icons.Rounded.Settings,
                    onNavigationIconClicked = {
                        navController.popBackStack(
                            route = ClothesScreens.MainScreen.name,
                            inclusive = false
                        )
                    },
                    onActionIconClicked = {

                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxSize(),
                    // caused by error about infinity maximum height constrains
                    //.verticalScroll(scrollState),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Here's what the AI has found...",
                        color = Color.White,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        items(items = imagesFromServer.value) { image ->
                            MatchingImage(image)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MatchingImage(image: Bitmap?) {
    Surface(
        modifier = Modifier
            .size(width = 330.dp, height = 370.dp),
        shape = RoundedCornerShape(35.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Result from model",
            contentScale = ContentScale.FillBounds
        )
    }
}