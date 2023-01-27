package com.example.clothesmatcher.screens.options

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Dns
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.screens.options.components.ChangeResultImagesNumber
import com.example.clothesmatcher.screens.options.components.ChangeUrlButtons
import com.example.clothesmatcher.screens.options.components.UrlTextField
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(optionsViewModel: OptionsViewModel, navController: NavHostController) {

    val defaultUrl = optionsViewModel.defaultUrl.collectAsState()

    val numImagesArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var expanded by remember {
        mutableStateOf(false)
    }

    var chosenNumber by remember {
        mutableStateOf(2)
    }

    val changeServerUrl = remember {
        mutableStateOf(false)
    }

    val newUrlState = remember(defaultUrl.value) {
        mutableStateOf(defaultUrl.value)
    }

    var showAddUrlTextField by remember {
        mutableStateOf(false)
    }

    var hideKeyboard by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            ClothesTopAppBar(
                title = "Settings",
                icon = Icons.Rounded.ArrowBackIos,
                onNavigationIconClicked = {
                    navController.popBackStack(ClothesScreens.MainScreen.name, false)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    hideKeyboard = true
                }
                .padding(top = it.calculateTopPadding() + 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {

                DisplayUrlOrTextField(
                    url = newUrlState.value,
                    showAddUrlTextField = showAddUrlTextField,
                    hideKeyboard = hideKeyboard,
                    onUrlClicked = {
                        changeServerUrl.value = true
                    },
                    onSearch = { newUrl ->
                        newUrlState.value = newUrl
                        changeServerUrl.value = false
                        showAddUrlTextField = false
                        optionsViewModel.addUrl(newUrl)
                    },
                    onFocusClear = {
                        hideKeyboard = false
                        changeServerUrl.value = false
                        showAddUrlTextField = false
                    }
                )


                Spacer(modifier = Modifier.height(30.dp))

                AnimatedVisibility(visible = changeServerUrl.value) {
                    ChangeUrlButtons(
                        modifier = Modifier.fillMaxWidth(),
                        onSelect = {
                            navController.navigate(ClothesScreens.UrlScreen.name)
                        },
                        onAdd = {
                            showAddUrlTextField = true
                        }
                    )
                }

                ChangeResultImagesNumber(
                    numImagesArray = numImagesArray,
                    chosenNumber = chosenNumber,
                    expanded = expanded,
                    onExpanded = {
                        expanded = !expanded
                    },
                    onNumChosen = { num ->
                        expanded = false
                        chosenNumber = num
                        optionsViewModel.numResults = num
                    }
                )
            }
        }
    }
}

@Composable
fun DisplayUrlOrTextField(
    url: String,
    showAddUrlTextField: Boolean,
    hideKeyboard: Boolean,
    onUrlClicked: () -> Unit,
    onSearch: (String) -> Unit,
    onFocusClear: () -> Unit
) {

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
                    onUrlClicked()
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AnimatedVisibility(visible = showAddUrlTextField) {
                UrlTextField(
                    modifier = Modifier.height(60.dp),
                    onSearch = { newUrl ->
                        onSearch(newUrl)
                    },
                    hideKeyboard = hideKeyboard,
                    onFocusClear = {
                        onFocusClear()
                    }
                )
            }
            AnimatedVisibility(visible = !showAddUrlTextField) {
                Text(
                    text = url,//newUrlState.value,
                    //modifier = Modifier.height(50.dp),
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

//
//@Preview
//@Composable
//fun OptionsPreview() {
//    ClothesMatcherTheme {
//        OptionsScreen()
//    }
//}