package com.tradistonks.app.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun Topbar(authController: AuthentificationController, openDrawer: () -> Unit, mainTitle: String){
    if(authController.user == null){
        Bar(
            title = mainTitle,
            buttonIcon = null
        ) { }
    }
    else{
        Bar(
            title = mainTitle,
            buttonIcon = Icons.Filled.Menu
        ) { openDrawer() }
    }

}

@Composable
fun Bar(title: String = "", buttonIcon: ImageVector?, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                if (buttonIcon != null) {
                    Icon(buttonIcon, contentDescription = "")
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}