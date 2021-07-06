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
import androidx.navigation.compose.rememberNavController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.RegisterContent
import com.tradistonks.app.components.SignInContent
import com.tradistonks.app.menu.Drawer
import com.tradistonks.app.menu.DrawerScreens
import com.tradistonks.app.ui.theme.colorPink


@Composable
fun Connexion(openDrawer: () -> Unit, navController: NavHostController) {
    Page(openDrawer, stringResource(R.string.connect), { pageConnexion(navController) })
}

@Composable
fun pageConnexion(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SignInContent()
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