package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.SignInContent
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.web.services.auth.AuthentificationController


@Composable
fun Connexion(
    openDrawer: () -> Unit,
    navController: NavHostController,
    authController: AuthentificationController
) {
    Page(authController, openDrawer, stringResource(R.string.connect), { pageConnexion(navController, authController) })
}

@Composable
fun pageConnexion(navController: NavHostController, authController: AuthentificationController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SignInContent(navController, authController)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("register")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
        ) {
            Text(
                text = "Create a new account"
            )
        }
    }
}