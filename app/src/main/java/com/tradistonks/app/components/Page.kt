package com.tradistonks.app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun Page(authController: AuthentificationController, openDrawer: () -> Unit, title: String, content: @Composable() () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Topbar(authController, openDrawer, title)
        TradistonksAndroidTheme {
            content()
        }
    }
}
