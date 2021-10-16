package com.tradistonks.app

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tradistonks.app.menu.Drawer
import com.tradistonks.app.menu.DrawerScreens
import com.tradistonks.app.pages.*
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.web.services.strategy.StrategyController
import kotlinx.coroutines.launch

@Composable
fun MainMenu(authController: AuthentificationController, stratController: StrategyController) {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }, authController = authController
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Connexion.route
            ) {
                composable(DrawerScreens.Account.route) {
                    Account(
                        openDrawer = {
                            openDrawer()
                        }, navController, authController
                    )
                }
                composable(DrawerScreens.History.route) {
                    History(
                        openDrawer = {
                            openDrawer()
                        }, authController = authController
                    )
                }
                composable(DrawerScreens.Strategies.route) {
                    Strategies(
                        openDrawer = {
                            openDrawer()
                        }, navController, authController = authController
                    )
                }
                composable(DrawerScreens.Dashboard.route) {
                    Dashboard(
                        openDrawer = {
                            openDrawer()
                        }, authController = authController
                    )
                }
                composable(DrawerScreens.Connexion.route) {
                    Connexion(
                        openDrawer = {
                            openDrawer()
                        }, navController, authController
                    )
                }
                composable(DrawerScreens.ModifyAccount.route) {
                    ModifyAccount(
                        openDrawer = {
                            openDrawer()
                        }, navController, authController
                    )
                }
                composable(DrawerScreens.Register.route) {
                    Register(
                        openDrawer = {
                            openDrawer()
                        }, navController, authController
                    )
                }
            }
        }
    }
}