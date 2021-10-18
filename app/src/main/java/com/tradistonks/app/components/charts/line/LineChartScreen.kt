package com.tradistonks.app.components.charts.line

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradistonks.app.components.charts.line.renderer.animation.simpleChartAnimation
import com.tradistonks.app.components.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.tradistonks.app.ui.theme.Margins
import com.tradistonks.app.ui.theme.Margins.horizontal
import com.tradistonks.app.ui.theme.Margins.vertical
import com.tradistonks.app.ui.theme.Margins.verticalLarge
import com.tradistonks.app.ui.theme.colors
import com.tradistonks.app.web.helper.LineChartHelper
import java.util.stream.Collectors

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

    lateinit var labels: List<String>
    if(LineChartHelper.isTimeStampValid(points[0].label)){
        labels = LineChartHelper.createNumericLabels(points, 5, true)
    }else{
        labels = LineChartHelper.createNumericLabels(points, 5, false)
    }

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
            horizontalOffset = lineChartDataModel.horizontalOffset,
        )
    }
}