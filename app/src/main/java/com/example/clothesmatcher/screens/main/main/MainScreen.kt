package com.example.clothesmatcher.screens.main.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@Preview
@Composable
fun MainScreen() {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ClothesTopAppBar(
                title = "Main Screen",
                icon = Icons.Rounded.Menu
            )
        }
    ) {
        ImagePicker(modifier = Modifier.padding(it))
    }
}

