package com.tradistonks.app.components.pages

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.Container
import com.tradistonks.app.components.charts.legend.LegendChartLineEntry
import com.tradistonks.app.components.charts.legend.LegendEntry
import com.tradistonks.app.components.charts.line.*
import com.tradistonks.app.components.charts.pie.PieChart
import com.tradistonks.app.components.charts.sample.buildValuePercentString
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.helper.LineChartHelper
import com.tradistonks.app.web.services.auth.AuthentificationController
import java.time.Instant
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.collections.ArrayList

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
    val pnl = strategy.results!!.pnl!!
    val timestamps = pnl.keys
    val lineChartDataWithTimestampList: ArrayList<LineChartDataWithTimestamp> = ArrayList()
    val points: ArrayList<PointWithTimestampLabel> = ArrayList()

    for (i in timestamps.indices) {
        val timestamp = timestamps.elementAt(i)

        for (symbol in pnl.getValue(timestamp)) {
            points.add(PointWithTimestampLabel(symbol.key, symbol.value, timestamp.toLong()))
        }
    }
    val symbols = points.stream().map(PointWithTimestampLabel::label).distinct().collect(Collectors.toList())

    points.map(PointWithTimestampLabel::timestamp).sortedDescending().reversed()
    for(symbol in symbols){
        lineChartDataWithTimestampList.add(LineChartDataWithTimestamp(
            points.stream().filter { point-> point.label == symbol }.collect(Collectors.toList())))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        Spacer(Modifier.height(defaultSpacerSize))
        LineChartScreenContentTimestamp(lineChartDataWithTimestampList, points, symbols)
        Spacer(Modifier.height(defaultSpacerSize))
    }
    Column(modifier = Modifier.padding(horizontal = defaultSpacerSize)){
        NavigateButtonStrategies(navController = navController)
    }
}
