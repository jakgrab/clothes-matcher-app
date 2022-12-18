package com.example.clothesmatcher.screens.options

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Dns
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import com.example.clothesmatcher.utils.matchIpOrUrl
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen() {

    val changeServerUrl = remember {
        mutableStateOf(false)
    }

    val newUrlState = remember {
        mutableStateOf("VEEEEEERY LOOOOOOGN OLDURL")
    }

    Scaffold(
        topBar = {
            ClothesTopAppBar(
                title = "Settings",
                icon = Icons.Rounded.ArrowBackIos,
                onNavigationIconClicked = {
                    // TODO popbackstack
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding() + 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Change server",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.width(70.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Dns,
                            contentDescription = "server icon",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .width(200.dp)
                            .background(color = MaterialTheme.colorScheme.surface)
                            .clickable {
                                changeServerUrl.value = true
                            },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (changeServerUrl.value) {

                            UrlTextField { newUrl ->
                                Log.d("tag", "New url/ip: $newUrl")
                                newUrlState.value = newUrl
                                changeServerUrl.value = false
                            }
                        } else {
                            Text(
                                text = newUrlState.value,
                                //modifier = Modifier.height(50.dp),
                                fontSize = 25.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UrlTextField(
    onSearch: (String) -> Unit
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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTextField(
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
        modifier = Modifier.height(60.dp),
        textStyle = TextStyle(fontSize = 20.sp),
        placeholder = {
            Text(text = placeholderText, fontSize = 20.sp)
        },
        isError = errorState.value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = true,
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview
@Composable
fun Preview() {
    ClothesMatcherTheme {
        OptionsScreen()
    }
}