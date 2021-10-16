package com.tradistonks.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.models.Strategy

@Composable
fun StrategyResultComponent(
    hasResults: Boolean,
    strategy: Strategy,
    modifier: Modifier
) {
    if (hasResults) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = modifier,
        ) {
            TextButton(onClick = {

            }) {
                Text("See all results")
            }
            TextButton(onClick = {

            }) {
                Text("See resume")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}