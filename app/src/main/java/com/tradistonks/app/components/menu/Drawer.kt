package com.tradistonks.app.components.menu


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.services.auth.AuthentificationController

sealed class DrawerScreens(val title: String, val route: String) {
    object Account : DrawerScreens("Account", "account")
    object Dashboard : DrawerScreens("Dashboard", "dashboard")
    object Strategies : DrawerScreens("Strategies", "strategies")
    object History : DrawerScreens("History", "history")
    object Connexion : DrawerScreens("Connexion", "connexion")
    object Register : DrawerScreens("Register", "register")
    object ModifyAccount : DrawerScreens("ModifyAccount", "modifyAccount")
    object StrategyResultSummary : DrawerScreens("StrategyResultSummary", "strategyResultSummary")
    object StrategyResult : DrawerScreens("StrategyResult", "strategyResult")
}

private val screens = listOf(
    DrawerScreens.Dashboard,
    DrawerScreens.Strategies,
    DrawerScreens.History,
    DrawerScreens.Account,
    DrawerScreens.Register,
    DrawerScreens.Connexion,
    DrawerScreens.ModifyAccount,
    DrawerScreens.StrategyResultSummary,
    DrawerScreens.StrategyResult
)

private val notDisplayedScreens = listOf(
    DrawerScreens.Register,
    DrawerScreens.Connexion,
    DrawerScreens.ModifyAccount,
    DrawerScreens.StrategyResultSummary,
    DrawerScreens.StrategyResult,
    DrawerScreens.Dashboard
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    authController: AuthentificationController
) {
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp)
        ) {
            if(authController.user != null){
                screens.forEach { screen ->
                    if(!notDisplayedScreens.contains(screen) ){
                        Spacer(Modifier.height(42.dp))
                        Text(
                            text = screen.title,
                            style = MaterialTheme.typography.h6,
                            color = colorPink,
                            modifier = Modifier.clickable {
                                onDestinationClicked(screen.route)
                            }
                        )
                    }
                }
            }
        }
}