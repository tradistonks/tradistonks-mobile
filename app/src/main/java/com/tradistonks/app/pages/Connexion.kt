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
import com.tradistonks.app.components.SignInContent


@Composable
fun Connexion(openDrawer: () -> Unit) {
    Page(openDrawer, stringResource(R.string.connect), { pageConnexion() })
}

@Composable
fun pageConnexion(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SignInContent()
        Spacer(modifier = Modifier.height(16.dp))
        RegisterContent()
    }
}