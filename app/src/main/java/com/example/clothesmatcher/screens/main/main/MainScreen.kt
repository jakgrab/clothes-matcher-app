package com.example.clothesmatcher.screens.main.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel, navController: NavController) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ClothesTopAppBar(
                title = "Main Screen",
                icon = Icons.Rounded.Menu,
                actionIcon = Icons.Rounded.Settings,
                onNavigationIconClicked = {

                },
                onActionIconClicked = {

                },
            )
        }
    ) {
        ImagePicker(modifier = Modifier.padding(it), viewModel = mainViewModel, navController)
    }
}

