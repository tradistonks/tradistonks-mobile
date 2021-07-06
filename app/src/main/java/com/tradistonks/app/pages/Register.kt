package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.RegisterContent


@Composable
fun Register(openDrawer: () -> Unit) {
    Page(openDrawer, stringResource(R.string.connect), { pageRegister() })
}

@Composable
fun pageRegister(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        RegisterContent()
        Spacer(modifier = Modifier.height(16.dp))
    }
}