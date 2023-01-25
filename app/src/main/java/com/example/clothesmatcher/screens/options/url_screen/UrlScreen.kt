package com.example.clothesmatcher.screens.options.url_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.navigation.ClothesScreens
import com.example.clothesmatcher.room.UrlEntity
import com.example.clothesmatcher.screens.options.OptionsViewModel
import com.example.clothesmatcher.ui.theme.ClothesMatcherTheme
import com.example.clothesmatcher.widgets.ClothesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrlScreen(optionsViewModel: OptionsViewModel, navController: NavController) {

//    val mockUrlList = listOf(
//        UrlEntity("www.facebook.com"),
//        UrlEntity(BASE_URL),
//        UrlEntity("https://ngrok-2dscv.com"),
//        UrlEntity("https://ngrok-2dscv.com"),
//        UrlEntity("https://ngrok-2dscv.com")
//    )

    val urlList = optionsViewModel.urlList.collectAsState()

    val isUrlListNotEmpty by remember(urlList.value.size) {
        mutableStateOf(urlList.value.isNotEmpty())
    }
//    val isUrlListNotEmpty by remember(mockUrlList.size) {
//        mutableStateOf(mockList2.isNotEmpty())
//    }

    Scaffold(
        topBar = {
            ClothesTopAppBar(
                title = "Choose Url",
                icon = Icons.Rounded.ArrowBackIos,
                onNavigationIconClicked = {
                    navController.popBackStack()
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
            AnimatedVisibility(visible = isUrlListNotEmpty) {
                SelectUrlList(
                    modifier = Modifier.padding(16.dp),
                    urlList = urlList.value,
                    onMarkAsDefaultClicked = { chosenUrl ->
                        optionsViewModel.setToBaseUrl(chosenUrl)
                        optionsViewModel.getDefaultUrl()
                        navController.navigate(ClothesScreens.OptionsScreen.name)
                        Log.d("URL", "Set as default: $chosenUrl")
                    },
                    onDeleteCLicked = { urlToDelete ->
                        Log.d("URL", "To delete: $urlToDelete")
                        optionsViewModel.deleteUrl(urlToDelete)
                    }
                )
            }
            AnimatedVisibility(visible = !isUrlListNotEmpty) {
                EmptyUrlListMessage()
            }
        }
    }
}

@Composable
fun SelectUrlList(
    modifier: Modifier = Modifier,
    urlList: List<UrlEntity>,
    onMarkAsDefaultClicked: (UrlEntity) -> Unit,
    onDeleteCLicked: (UrlEntity) -> Unit
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(items = urlList) { url ->
                UrlItem(
                    url = url,
                    onMarkAsDefaultClicked = {
                        onMarkAsDefaultClicked(url)
                    },
                    onDeleteCLicked = {
                        onDeleteCLicked(url)
                    }
                )
                Divider(thickness = 1.dp)
            }
        }
    }
}

@Composable
fun EmptyUrlListMessage() {
    Row(modifier = Modifier.width(200.dp)) {
        Text(text = "Nothing to show :(", fontSize = 20.sp)
    }
}

@Composable
fun UrlItem(
    url: UrlEntity,
    onMarkAsDefaultClicked: () -> Unit,
    onDeleteCLicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val animatedHeight by animateDpAsState(
        if (expanded) 120.dp else 60.dp
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .padding(6.dp)
            .background(Color(0xFF232125))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                expanded = !expanded
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(6.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = url.url,
                fontSize = 18.sp,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
        }
        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF232125)
                ),
                shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onMarkAsDefaultClicked() }) {
                        Icon(
                            imageVector = Icons.Rounded.Done,
                            contentDescription = "Mark as default",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = { onDeleteCLicked() }) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete url",
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.errorContainer
                        )
                    }
                }
            }
        }
    }
}
