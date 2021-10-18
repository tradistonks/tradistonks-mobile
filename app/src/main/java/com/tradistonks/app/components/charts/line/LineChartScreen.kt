package com.tradistonks.app.components.charts.line

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradistonks.app.ui.theme.Margins.horizontal
import com.tradistonks.app.ui.theme.Margins.vertical
import com.tradistonks.app.ui.theme.Margins.verticalLarge
import com.tradistonks.app.ui.theme.colors
import com.tradistonks.app.web.helper.LineChartHelper
import java.util.stream.Collectors
import com.tradistonks.app.components.charts.line.LineChartDataModel

@Composable
fun LineChartScreen(lineChartDataList: List<LineChartData>) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { ChartScreenStatus.navigateHome() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back to home")
                    }
                },
                title = { Text(text = "Line Chart") }
            )
        },
    ) { LineChartScreenContent(lineChartDataList) }
}


@Composable
fun LineChartScreenContentTimestamp(lineChartDataTimestampList: List<LineChartDataWithTimestamp>) {
    val lineChartDataModel = LineChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = vertical
        )
    ) {
        LineChartTimestampRow(lineChartDataTimestampList, lineChartDataModel)
        //HorizontalOffsetSelector(lineChartDataModel)
        OffsetProgress(lineChartDataModel)
    }
}

@Composable
fun LineChartScreenContent(lineChartDataList: List<LineChartData>) {
    val lineChartDataModel = LineChartDataModel()

    Column(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = vertical
        )
    ) {
        LineChartRow(lineChartDataList, lineChartDataModel)
        //HorizontalOffsetSelector(lineChartDataModel)
        OffsetProgress(lineChartDataModel)
    }
}

@Composable
fun HorizontalOffsetSelector(lineChartDataModel: LineChartDataModel) {
    val selectedType = lineChartDataModel.pointDrawerType

    Row(
        modifier = Modifier.padding(
            horizontal = horizontal,
            vertical = verticalLarge
        ),
        verticalAlignment = CenterVertically
    ) {
        Text("Point Drawer")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontal, vertical = vertical)
                .align(CenterVertically)
        ) {
            LineChartDataModel.PointDrawerType.values().forEach { type ->
                OutlinedButton(
                    border = ButtonDefaults.outlinedBorder.takeIf { selectedType == type },
                    onClick = { lineChartDataModel.pointDrawerType = type }
                ) {
                    Text(type.name)
                }
            }
        }
    }
}

@Composable
fun OffsetProgress(lineChartDataModel: LineChartDataModel) {
    Row(
        modifier = Modifier.padding(horizontal = horizontal),
        verticalAlignment = CenterVertically
    ) {
        Text("Offset")

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
        ) {
            Slider(
                value = lineChartDataModel.horizontalOffset,
                onValueChange = { lineChartDataModel.horizontalOffset = it },
                valueRange = 0f.rangeTo(25f)
            )
        }
    }
}

@Composable
fun LineChartRow(lineChartDataList: List<LineChartData>, lineChartDataModel: LineChartDataModel) {
    val listPoints: List<List<Point>> = lineChartDataList.stream().map{ l -> l.points}.collect(Collectors.toList())
    val points: List<Point> = listPoints.flatMap { it.toList() }
    val labels: List<String> = points.stream().map(Point::label).distinct().collect(Collectors.toList())

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        LineChart(
            colors = colors,
            labels = labels,
            allPoints = points,
            lineChartDataList = lineChartDataList,
            horizontalOffset = lineChartDataModel.horizontalOffset
        )
    }
}

@Composable
fun LineChartTimestampRow(lineChartDataTimestampList: List<LineChartDataWithTimestamp>, lineChartDataModel: LineChartDataModel) {
    val listPoints: List<List<PointWithTimestampLabel>> = lineChartDataTimestampList.stream().map{ l -> l.points}.collect(Collectors.toList())
    val points: List<PointWithTimestampLabel> = listPoints.flatMap { it.toList() }
    val labels: List<String> = LineChartHelper.createLineChartLabelsTimestamp(points, step= 5)
    val minAndMaxLabels = LineChartHelper.findMinMaxOfLabelsLong(points.map(PointWithTimestampLabel::timestamp))

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        LineChartTimestamp(
            colors = colors,
            labels = labels,
            allPoints = points,
            lineChartDataList = lineChartDataTimestampList,
            horizontalOffset = lineChartDataModel.horizontalOffset,
            minAndMaxLabelValue = minAndMaxLabels
        )
    }
}