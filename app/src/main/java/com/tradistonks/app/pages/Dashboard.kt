package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.charts.sample.PieSampleData
import com.tradistonks.app.components.charts.sample.TableSampleData
import com.tradistonks.app.components.charts.sample.pie.PieStyledScreen
import com.tradistonks.app.components.charts.sample.table.TableStyledScreen

@Composable
fun Dashboard(openDrawer: () -> Unit) {
    Page(openDrawer, stringResource(R.string.title_page_dashboard), { PageDashboard() })
}

@Composable
fun PageDashboard(){
    DashboardContent()
}


private val defaultSpacerSize = 16.dp

@Composable
fun DashboardContent( modifier: Modifier = Modifier) {
    Spacer(Modifier.height(defaultSpacerSize))
    LazyColumn(
        modifier = modifier.padding(horizontal = defaultSpacerSize)
    ) {
        item {
            TableStyledScreen(TableSampleData[1])
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(PieSampleData[0])
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(PieSampleData[0])
            Spacer(Modifier.height(defaultSpacerSize))
        }
        item {
            PieStyledScreen(PieSampleData[0])
            Spacer(Modifier.height(defaultSpacerSize))
        }
    }
}