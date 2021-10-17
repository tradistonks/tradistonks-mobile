package com.tradistonks.app.components.charts.line

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tradistonks.app.components.charts.line.renderer.animation.simpleChartAnimation
import com.tradistonks.app.components.charts.line.renderer.line.SolidLineDrawer
import com.tradistonks.app.components.charts.line.renderer.point.FilledCircularPointDrawer
import com.tradistonks.app.components.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.yaxis.SimpleYAxisDrawer

@Composable
fun MyLineChartStrategy() {

    LineChart(
        lineChartData = LineChartData(points = listOf(Point(1f,"Label 1"), Point(2f,"Label 2"))),
    // Optional properties.
    modifier = Modifier.fillMaxSize(),
    animation = simpleChartAnimation(),
    pointDrawer = FilledCircularPointDrawer(),
    lineDrawer = SolidLineDrawer(),
    xAxisDrawer = SimpleXAxisDrawer(),
    yAxisDrawer = SimpleYAxisDrawer(),
    horizontalOffset = 5f
    )
}