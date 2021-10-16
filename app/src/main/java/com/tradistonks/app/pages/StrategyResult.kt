package com.tradistonks.app.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun StrategyResult(
    openDrawer: () -> Unit,
    navController: NavHostController,
    authController: AuthentificationController,
    strategy: Strategy
) {
    Page(authController, openDrawer, stringResource(R.string.title_page_strategy_results), { pageStrategyResult(strategy, navController, authController) })
}

@Composable
fun pageStrategyResult(
    strategy: Strategy,
    navController: NavHostController,
    authController: AuthentificationController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController

    }
}