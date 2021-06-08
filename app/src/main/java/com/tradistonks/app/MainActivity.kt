package com.tradistonks.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.android.material.navigation.NavigationView
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tradistonks.app.navigation.Account
import com.tradistonks.app.navigation.Drawer
import com.tradistonks.app.navigation.DrawerScreens
import com.tradistonks.app.navigation.Home
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val navControler = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navControler)
    }
}

@Composable
fun AppMainScreen() {
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
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route
            ) {
                composable(DrawerScreens.Home.route) {
                    Home(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Account.route) {
                    Account(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TradistonksAndroidTheme {
        AppMainScreen()
    }
}