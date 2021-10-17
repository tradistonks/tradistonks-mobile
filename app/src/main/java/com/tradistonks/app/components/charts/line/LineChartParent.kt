package com.tradistonks.app.components.charts.line

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tradistonks.app.components.charts.line.renderer.animation.simpleChartAnimation
import com.tradistonks.app.components.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.tradistonks.app.ui.theme.Margins
import com.tradistonks.app.ui.theme.colors
import java.util.stream.Collectors



@Composable
fun MyLineChartStrategy(modifier: Modifier) {
    val lineChartDataList = listOf(
        LineChartData(points = listOf(Point(1f,"Label 1"), Point(3f,"Label 2"), Point(3f,"Label 3"))),
        LineChartData(points = listOf(Point(0.5f,"Label 1"), Point(6f,"Label 2"))),
        LineChartData(points = listOf(Point(2f,"Label 1"), Point(0.5f,"Label 2"))),
        LineChartData(points = listOf(Point(3f,"Label 1"), Point(4f,"Label 2")))
    )
    val listPoints: List<List<Point>> = lineChartDataList.stream().map{ l -> l.points}.collect(Collectors.toList())
    val points: List<Point> = listPoints.flatMap { it.toList() }
    val labels: List<String> = points.stream().map(Point::label).distinct().collect(Collectors.toList())

    LineChart(
        colors = colors,
        lineChartDataList = lineChartDataList,
        labels = labels,
        allPoints = points,
        // Optional properties.
        modifier = modifier.padding(
            horizontal = Margins.horizontal,
            vertical = Margins.vertical
        ),
        animation = simpleChartAnimation(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}