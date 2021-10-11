package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.RegisterContent
import com.tradistonks.app.web.services.auth.AuthentificationController


@Composable
fun Register(openDrawer: () -> Unit, navController: NavHostController, authController: AuthentificationController) {
    Page(authController, openDrawer, stringResource(R.string.connect), { pageRegister(navController, authController) })
}

@Composable
fun pageRegister(navController: NavHostController, authController: AuthentificationController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        RegisterContent(navController, authController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}