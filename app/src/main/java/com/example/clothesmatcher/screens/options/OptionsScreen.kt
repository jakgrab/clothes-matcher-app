package com.example.clothesmatcher.screens.options

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Dns
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothesmatcher.room.UrlEntity
import com.example.clothesmatcher.screens.options.components.ChangeUrlButtons
import com.example.clothesmatcher.screens.options.components.UrlTextField
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen() {//optionsViewModel: OptionsViewModel, navController: NavHostController) {

    //val urlListState = optionsViewModel.urlList.collectAsState()

    /* TODO Upscale all components to fit UrlTextField,
    *  TODO cancel option for entering new url,
    *  TODO when select url button clicked - show list from database
    *  */


    val changeServerUrl = remember {
        mutableStateOf(false)
    }

    val newUrlState = remember {
        mutableStateOf("VEEEEEERY LOOOOOOGN OLDURL")
    }

    var showAddUrlTextField by remember {
        mutableStateOf(false)
    }

    var showSelectList by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            ClothesTopAppBar(
                title = "Settings",
                icon = Icons.Rounded.ArrowBackIos,
                onNavigationIconClicked = {
                    // navController.popBackStack()
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
                                Log.d("URL", "CLICKED ")
                            },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (!showAddUrlTextField) {
                            Text(
                                text = newUrlState.value,
                                //modifier = Modifier.height(50.dp),
                                fontSize = 25.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        } else {
                            UrlTextField { newUrl ->
                                Log.d("tag", "New url/ip: $newUrl")
                                newUrlState.value = newUrl
                                changeServerUrl.value = false
                                showAddUrlTextField = false
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (changeServerUrl.value) {
                    Log.d("URL", "Change server url value: ${changeServerUrl.value}")
                    ChangeUrlButtons(
                        modifier = Modifier.fillMaxWidth(),
                        onSelect = {
                            showSelectList = true
                        },
                        onAdd = {
                            showAddUrlTextField = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectUrlList(modifier: Modifier = Modifier, urlList: List<UrlEntity>) {
    Column(modifier = modifier) {
        LazyColumn() {
            items(items = urlList) { url ->

            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    ClothesMatcherTheme {
        OptionsScreen()
    }
}