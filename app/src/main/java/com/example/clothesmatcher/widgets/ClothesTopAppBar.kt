package com.example.clothesmatcher.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesTopAppBar(
    title: String = "Screen",
    icon: ImageVector? = null,
    actionIcon: ImageVector? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationIconClicked: () -> Unit = {},
    onActionIconClicked: () -> Unit = {}
) {

    CenterAlignedTopAppBar(
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onNavigationIconClicked.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            } else {
                Box {}
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = { onActionIconClicked.invoke() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = null
                    )
                }
            } else {
                Box {}
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.Green,
            actionIconContentColor = Color.White
        ),
        scrollBehavior = scrollBehavior
    )

}