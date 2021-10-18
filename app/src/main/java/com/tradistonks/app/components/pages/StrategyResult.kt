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
import com.tradistonks.app.components.charts.line.*
import com.tradistonks.app.components.charts.line.LineChartScreenContent
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.services.auth.AuthentificationController
import java.time.Instant
import java.util.*

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
        LineChartData(points = listOf(Point(1f, "label 1"), Point(3f,"label 2"), Point(3f,"label 3"))),
        LineChartData(points = listOf(Point(0.5f,"label 1"), Point(6f,"label 2"))),
        /*LineChartData(points = listOf(Point(2f,"Label 1"), Point(0.5f,"Label 2"))),
        LineChartData(points = listOf(Point(3f,"Label 1"), Point(4f,"Label 2")))*/
    )
    val lineChartDataWithTimestampList = listOf(
        LineChartDataWithTimestamp(points = listOf(PointWithTimestampLabel(1, 1632340800), PointWithTimestampLabel(3,1634131800), PointWithTimestampLabel(3,1634131800))),
        LineChartDataWithTimestamp(points = listOf(PointWithTimestampLabel(0.5,1634132100), PointWithTimestampLabel(6,1634132100))),
    )

    println(lineChartDataWithTimestampList.toString())

    /*Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        Spacer(Modifier.height(defaultSpacerSize))
        LineChartScreenContent(lineChartDataList)
        Spacer(Modifier.height(defaultSpacerSize))
        NavigateButtonStrategies(navController)
    }*/

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        Spacer(Modifier.height(defaultSpacerSize))
        LineChartScreenContentTimestamp(lineChartDataWithTimestampList)
        Spacer(Modifier.height(defaultSpacerSize))
        NavigateButtonStrategies(navController)
    }
}