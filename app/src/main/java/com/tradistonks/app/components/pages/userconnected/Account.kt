package com.tradistonks.app.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.colorYellow
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun Account(openDrawer: () -> Unit, navController: NavHostController, authController: AuthentificationController) {
    Page(authController, openDrawer, stringResource(R.string.title_page_account), { pageAccount(navController, authController)})
}

@Composable
fun pageAccount(navController: NavHostController, authController: AuthentificationController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        displayAccountInfo(navController, authController)
    }
}

@Composable
fun displayAccountInfo(navController: NavHostController, authController: AuthentificationController){
    val user = authController.user
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.my_profil),
            style = MaterialTheme.typography.h1,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = stringResource(id = R.string.my_profil),
                Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(){
                Column() {
                    Text(
                        text = "Username : " + user!!.username,
                        style = MaterialTheme.typography.h2,
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Email : " + user.email,
                        style = MaterialTheme.typography.h2,
                        color = textColor
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("modifyAccount")
            },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
        ) {
            Text(text = stringResource(id = R.string.modify_account))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authController.user = null
                authController.token = null
                navController.navigate("connexion")
            },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorYellow)
        ) {
            Text(text = stringResource(id = R.string.logout))
        }
    }
}