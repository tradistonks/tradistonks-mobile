package com.tradistonks.app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme

@Composable
fun Page(openDrawer: () -> Unit, title: String, content: @Composable() () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Topbar(openDrawer, title)
        TradistonksAndroidTheme {
            content()
        }
    }
}
