package com.example.clothesmatcher.screens.options.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    errorState: MutableState<Boolean>,
    placeholderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        value = valueState.value,
        onValueChange = { url ->
            valueState.value = url
        },
        modifier = modifier,
        textStyle = TextStyle(fontSize = 20.sp),
        placeholder = {
            Text(text = placeholderText, fontSize = 20.sp)
        },
        isError = errorState.value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp)
    )
}