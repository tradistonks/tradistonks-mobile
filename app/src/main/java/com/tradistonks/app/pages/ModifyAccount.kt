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
import com.tradistonks.app.components.form.modifyInfoUserForm
import com.tradistonks.app.web.services.auth.AuthentificationController


@Composable
fun ModifyAccount(
    openDrawer: () -> Unit,
    navController: NavHostController,
    authController: AuthentificationController
) {
    Page(openDrawer, stringResource(R.string.connect), { pageModifyInfo(navController, authController) })
}

@Composable
fun pageModifyInfo(navController: NavHostController, authController: AuthentificationController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        modifyInfoUserForm(navController, authController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}