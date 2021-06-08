package com.tradistonks.app.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Topbar(openDrawer: () -> Unit, mainTitle: String){
    Bar(
        title = mainTitle,
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() }
    )
}

@Composable
fun Bar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}