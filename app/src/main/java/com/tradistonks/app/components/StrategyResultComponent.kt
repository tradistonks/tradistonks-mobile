package com.tradistonks.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.tradistonks.app.R
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.ui.theme.textColor

@Composable
fun StrategyResultComponent(
    hasResults: Boolean,
    strategyId: String,
    modifier: Modifier,
    navController: NavHostController
) {
    fun navigateToStrategySummary(strategyId: String) {
        navController.navigate("strategyResultSummary/$strategyId")
    }

    fun navigateToStrategyResults(strategyId: String) {
        navController.navigate("strategyResult/$strategyId")
    }

    if (hasResults) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            TextButton(onClick = {
                navigateToStrategyResults(strategyId)
            }) {
                Text("See all results")
            }
            TextButton(onClick = {
                navigateToStrategySummary(strategyId)
            }) {
                Text("See orders resume")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
