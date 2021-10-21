package com.tradistonks.app.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.sample.pie.PieStyledScreen
import com.tradistonks.app.components.charts.sample.table.TableStyledScreen
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.ui.theme.*
import com.tradistonks.app.web.helper.ChartHelper
import com.tradistonks.app.web.helper.PieChartHelper
import com.tradistonks.app.web.helper.TableChartHelper
import com.tradistonks.app.web.services.auth.AuthentificationController

@Composable
fun StrategyResultSummary(
    openDrawer: () -> Unit,
    authController: AuthentificationController,
    strategy: Strategy,
    navController: NavHostController
) {
    Page(authController, openDrawer, stringResource(R.string.title_page_strategy_summary), { pageStrategyResultSummary(strategy, navController) })
}
private val defaultSpacerSize = 16.dp

@Composable
fun pageStrategyResultSummary(
    strategy: Strategy,
    navController: NavHostController
) {
    Spacer(Modifier.height(defaultSpacerSize))
    LazyColumn(
        modifier = Modifier.padding(horizontal = defaultSpacerSize)
    ) {
        val categories = ChartHelper.retrieveLegendOfData(strategy.results!!.orders!!)
        val pieDataStrategy = PieChartHelper.retrievePieChartData(categories, strategy.results!!.orders!!)
        val buyData = pieDataStrategy["Buy"]
        val sellData = pieDataStrategy["Sell"]
        val dataBuy = ChartHelper.retrieveSumsOfTypeBySymbol(strategy.results!!.orders!!, categories, "Buy")
        val dataSell = ChartHelper.retrieveSumsOfTypeBySymbol(strategy.results!!.orders!!, categories, "Sell")
        item{
            DisplayMainTitleStrategySummary(strategyName = strategy.name)
        }
        item {
            TableStyledScreen(TableChartHelper.retrieveTableChartData(categories, dataBuy)[1], "Quantity Bought")
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(buyData!![0], "Quantity Bought Pourcentage")
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item{
            DisplaySecondaryTitleStrategySummary()
        }
        item {
            TableStyledScreen(TableChartHelper.retrieveTableChartData(categories, dataSell)[1], "Quantity Sold Pourcentage")
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(sellData!![0], "Sell Pourcentage")
            NavigateButtonStrategies(navController)
        }
    }
}

@Composable
fun DisplayMainTitleStrategySummary(strategyName: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(defaultSpacerSize))
        Text(
            text = strategyName.uppercase(),
            style = MaterialTheme.typography.h2,
            color = colorWhite,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(defaultSpacerSize))
        Text(
            text = "Orders Bought",
            style = MaterialTheme.typography.h1,
            color = colorBlue,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(defaultSpacerSize))
    }
}

@Composable
fun DisplaySecondaryTitleStrategySummary(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(defaultSpacerSize))
        Text(
            text = "Orders Sold",
            style = MaterialTheme.typography.h1,
            color = colorBlue,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(defaultSpacerSize))
    }
}

@Composable
fun NavigateButtonStrategies(navController: NavHostController){

    Spacer(Modifier.height(defaultSpacerSize))
    Button(
        onClick = {
            navController.navigate("strategies")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorPink),
    ) {
        Icon(
            Icons.Outlined.KeyboardArrowLeft,
            contentDescription = "",
            tint = colorBlack
        )
    }
    Spacer(Modifier.height(defaultSpacerSize))
}
