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
import com.tradistonks.app.components.charts.line.LineChartData
import com.tradistonks.app.components.charts.line.LineChartScreenContent
import com.tradistonks.app.components.charts.line.MyLineChartStrategy
import com.tradistonks.app.components.charts.line.Point
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
    val lineChartDataList = listOf(
        LineChartData(points = listOf(Point(1f,"Label 1"), Point(3f,"Label 2"), Point(3f,"Label 3"))),
        LineChartData(points = listOf(Point(0.5f,"Label 1"), Point(6f,"Label 2"))),
        LineChartData(points = listOf(Point(2f,"Label 1"), Point(0.5f,"Label 2"))),
        LineChartData(points = listOf(Point(3f,"Label 1"), Point(4f,"Label 2")))
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        Spacer(Modifier.height(defaultSpacerSize))
        //MyLineChartStrategy(Modifier)
        LineChartScreenContent(lineChartDataList)
        Spacer(Modifier.height(defaultSpacerSize))

    }
}