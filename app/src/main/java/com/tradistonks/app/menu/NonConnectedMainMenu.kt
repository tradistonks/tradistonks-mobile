package com.tradistonks.app

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tradistonks.app.menu.Drawer
import com.tradistonks.app.menu.DrawerScreens
import com.tradistonks.app.menu.NonConnectedDrawer
import com.tradistonks.app.pages.*
import kotlinx.coroutines.launch

@Composable
fun NonConnectedMainMenu() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        NavHost(
            navController = navController,
            startDestination = DrawerScreens.Connexion.route
        ) {
            composable(DrawerScreens.Connexion.route) {
                Connexion(
                    openDrawer = {
                        openDrawer()
                    }, navController
                )
            }
            composable(DrawerScreens.Register.route) {
                Register(
                    openDrawer = {
                        openDrawer()
                    }, navController
                )
            }
            composable(DrawerScreens.Account.route) {
                Account(
                    openDrawer = {
                        openDrawer()
                    }, navController
                )
            }
        }
    }
}