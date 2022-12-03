package com.example.clothesmatcher.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClothesTopAppBar(
    title: String = "Screen",
    icon: ImageVector? = null,
    actionIcon: ImageVector? = null,
    colors: TopAppBarColors? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationIconClicked: () -> Unit = {},
    onActionIconClicked: () -> Unit = {}
) {
    ClothesMatcherTheme {

        val defaultTopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )

        val topAppBarColors = colors ?: defaultTopAppBarColors

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            navigationIcon = {
                if (icon != null) {
                    IconButton(onClick = { onNavigationIconClicked.invoke() }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
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
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                } else {
                    Box {}
                }
            },
            colors = topAppBarColors,
            scrollBehavior = scrollBehavior
        )
    }
}