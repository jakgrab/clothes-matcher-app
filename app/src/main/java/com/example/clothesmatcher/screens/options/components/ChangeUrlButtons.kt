package com.example.clothesmatcher.screens.options.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChangeUrlButtons(
    modifier: Modifier = Modifier,
    onSelect: () -> Unit = {},
    onAdd: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OptionsButton(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .padding(6.dp),
            text = "Select Url"
        ) {
            onSelect()
        }
        OptionsButton(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .padding(6.dp),
            text = "Add New Url"
        ) {
            onAdd()
        }
    }
}

@Composable
private fun OptionsButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
    ) {
        Text(text = text)
    }
}