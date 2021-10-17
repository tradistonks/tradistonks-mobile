package com.tradistonks.app.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.line.MyLineChartStrategy
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.services.auth.AuthentificationController

private val defaultSpacerSize = 16.dp

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
        Spacer(Modifier.height(defaultSpacerSize))
        MyLineChartStrategy()
        Spacer(Modifier.height(defaultSpacerSize))

    }
}