package com.tradistonks.app.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.sample.pie.PieStyledScreen
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.helper.PieChartHelper
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun StrategyResultSummary(
    openDrawer: () -> Unit,
    authController: AuthentificationController,
    strategy: Strategy
) {
    Page(authController, openDrawer, stringResource(R.string.title_page_strategy_summary), { pageStrategyResultSummary(strategy) })
}
private val defaultSpacerSize = 16.dp

@Composable
fun pageStrategyResultSummary(
    strategy: Strategy
) {
    Spacer(Modifier.height(defaultSpacerSize))
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultSpacerSize)
    ) {
        val pieDataStrategy = PieChartHelper.retrievePieChartData(strategy.results!!.orders!!)
        val buyData = pieDataStrategy["Buy"]
        val sellData = pieDataStrategy["Sell"]
        item {
            PieStyledScreen(buyData!![0], "Buy Pourcentage")
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(sellData!![0], "Sell Pourcentage")
            Spacer(Modifier.height(defaultSpacerSize))
        }
        /*item {
            TableStyledScreen(TableSampleData[1])
            Spacer(Modifier.height(defaultSpacerSize))
        }*/
    }
}