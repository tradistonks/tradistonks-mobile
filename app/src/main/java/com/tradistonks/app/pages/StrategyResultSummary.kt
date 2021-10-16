package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.sample.PieSampleData
import com.tradistonks.app.components.charts.sample.TableSampleData
import com.tradistonks.app.components.charts.sample.pie.PieStyledScreen
import com.tradistonks.app.components.charts.sample.table.TableStyledScreen
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.helper.PieChartHelper
import com.tradistonks.app.web.services.auth.AuthentificationController
import java.util.*

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
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultSpacerSize)
    ) {
        val pieDataStrategy = PieChartHelper.retrievePieChartData(strategy.results!!.orders!!)
        val buyData = pieDataStrategy["Buy"]
        val sellData = pieDataStrategy["Sell"]
        item {
            Text(
                text = "Buy Pourcentage for each currency",
                style = MaterialTheme.typography.h2,
                color = textColor
            )
            PieStyledScreen(buyData!![0])
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            Text(
                text = "Sell Pourcentage for each currency",
                style = MaterialTheme.typography.h2,
                color = textColor
            )
            PieStyledScreen(sellData!![0])
            Spacer(Modifier.height(defaultSpacerSize))
        }
        /*item {
            TableStyledScreen(TableSampleData[1])
            Spacer(Modifier.height(defaultSpacerSize))
        }*/
    }
}