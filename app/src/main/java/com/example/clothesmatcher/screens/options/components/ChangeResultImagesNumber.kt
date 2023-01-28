package com.example.clothesmatcher.screens.options.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChangeResultImagesNumber(
    numImagesArray: Array<Int>,
    chosenNumber: Int,
    expanded: Boolean,
    onExpanded: () -> Unit,
    onNumChosen: (Int) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Number of Results",
            fontSize = 20.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
        )
        NumImagesDropdown(chosenNumber, expanded) {
            onExpanded()
        }
    }
    AnimatedVisibility(visible = expanded, enter = expandVertically(), exit = shrinkVertically()) {
        NumImagesList(numImagesList = numImagesArray) { rate ->
            onNumChosen(rate)
        }
    }

}


@Composable
fun NumImagesDropdown(chosenRate: Int, expanded: Boolean, onArrowIconClicked: () -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$chosenRate",
            fontSize = 20.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
        )
        IconButton(
            onClick = {
                Log.d("DD", "Clicked")
                onArrowIconClicked()
            }
        ) {
            Icon(
                imageVector = if (!expanded)
                    Icons.Rounded.ArrowDropDown
                else Icons.Rounded.ArrowDropUp,
                contentDescription = "choose sampling rate",
                modifier = Modifier.size(50.dp),
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
        }
    }

}

@Composable
fun NumImagesList(numImagesList: Array<Int>, onNumChosen: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Card(
            border = BorderStroke(width = 1.dp, color = Color.DarkGray),
            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
        ) {
            LazyColumn(
                modifier = Modifier.width(100.dp)
            ) {
                items(numImagesList) { num ->
                    NumImagesItem(num) { chosenNum ->
                        onNumChosen(chosenNum)
                    }
                }
            }
        }
    }
}

@Composable
fun NumImagesItem(numOfImages: Int, onNumClicked: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
            .clickable {
                Log.d("DD", "Rate: $numOfImages")
                onNumClicked(numOfImages)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$numOfImages",
            fontSize = 20.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
        )
    }
    Divider(modifier = Modifier.fillMaxWidth(), color = Color.DarkGray)
}
