package com.tradistonks.app.menu


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import com.tradistonks.app.ui.theme.textColor

sealed class DrawerScreens(val title: String, val route: String) {
    object Account : DrawerScreens("Account", "account")
    object Dashboard : DrawerScreens("Dashboard", "dashboard")
    object Strategies : DrawerScreens("Strategies", "strategies")
    object History : DrawerScreens("History", "history")
    object Connexion : DrawerScreens("Connexion", "connexion")
    object Register : DrawerScreens("Register", "register")
    object ModifyAccount : DrawerScreens("ModifyAccount", "modifyAccount")
}

private val screens = listOf(
    DrawerScreens.Dashboard,
    DrawerScreens.Strategies,
    DrawerScreens.History,
    DrawerScreens.Account,
    DrawerScreens.Register,
    DrawerScreens.Connexion,
    DrawerScreens.ModifyAccount
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp)
        ) {
            screens.forEach { screen ->
                if(screen != DrawerScreens.Register
                    && screen != DrawerScreens.Connexion
                    && screen != DrawerScreens.ModifyAccount){

                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = screen.title,
                        style = MaterialTheme.typography.h1,
                        color = textColor,
                        modifier = Modifier.clickable {
                            onDestinationClicked(screen.route)
                        }
                    )
                }
            }
        }
}

@Preview
@Composable
fun DrawerPreview() {
    TradistonksAndroidTheme {
        Drawer{}
    }
}