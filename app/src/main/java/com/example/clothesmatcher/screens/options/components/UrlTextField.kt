package com.example.clothesmatcher.screens.options.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.clothesmatcher.utils.matchIpOrUrl

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UrlTextField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    hideKeyboard: Boolean,
    onFocusClear: () -> Unit,
) {
    val userProvidedUrlState = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val validState = remember(userProvidedUrlState.value) {
        userProvidedUrlState.value.trim().isNotEmpty() && matchIpOrUrl(userProvidedUrlState.value)
    }
    val errorState = remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    MyTextField(
        modifier = modifier,
        valueState = userProvidedUrlState,
        errorState = errorState,
        placeholderText = "New server Url",
        onAction = KeyboardActions {
            if (!validState) {
                errorState.value = true
                return@KeyboardActions
            }
            onSearch(userProvidedUrlState.value.trim())
            userProvidedUrlState.value = ""
            keyboardController?.hide()
            focusManager.clearFocus()
            errorState.value = false
        }
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}
