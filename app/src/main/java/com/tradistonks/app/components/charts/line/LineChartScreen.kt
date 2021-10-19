package com.tradistonks.app.components.charts.line

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradistonks.app.components.charts.ChartShape
import com.tradistonks.app.components.charts.Container
import com.tradistonks.app.components.charts.internal.safeGet
import com.tradistonks.app.components.charts.legend.LegendChartLineEntry
import com.tradistonks.app.ui.theme.Margins.horizontal
import com.tradistonks.app.ui.theme.Margins.vertical
import com.tradistonks.app.ui.theme.Margins.verticalLarge
import com.tradistonks.app.ui.theme.colors
import com.tradistonks.app.web.helper.LineChartHelper

import java.util.stream.Collectors

@Composable
fun LineChartScreenContentTimestamp(
    lineChartDataTimestampList: List<LineChartDataWithTimestamp>,
    points: ArrayList<PointWithTimestampLabel>,
    symbols: MutableList<String>
) {
    val lineChartDataModel =
        com.tradistonks.app.components.charts.line.LineChartDataModel()
    Container(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .animateContentSize(),
        title = "All Results"
    ) {
        LineChartTimestampRow(lineChartDataTimestampList, points, lineChartDataModel)
        Spacer(Modifier.height(50.dp))
        OffsetProgress(lineChartDataModel)

        val entries = createEntriesForLegend(symbols)
        CustomVerticalLegend(entries)
    }
}

fun createEntriesForLegend(symbolsList: List<String>): List<LegendChartLineEntry> =
    symbolsList.mapIndexed { index, item ->
        LegendChartLineEntry(
            text = item,
            shape = ChartShape(
                color = colors.safeGet(index),
                shape = CircleShape,
                size = 8.dp,
            )
        )
    }
@Composable
private fun CustomVerticalLegend(entries: List<LegendChartLineEntry>) {
    Column {
        entries.forEachIndexed { idx, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                Box(
                    Modifier
                        .requiredSize(item.shape.size)
                        .background(item.shape.color, item.shape.shape)
                )

                Spacer(modifier = Modifier.requiredSize(8.dp))
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp),
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            if (idx != entries.lastIndex)
                Divider()
        }
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
                .padding(horizontal = horizontal + 5.dp, vertical = vertical)
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
fun LineChartTimestampRow(
    lineChartDataTimestampList: List<LineChartDataWithTimestamp>,
    points: ArrayList<PointWithTimestampLabel>,
    lineChartDataModel: LineChartDataModel
) {
    lineChartDataModel.pointDrawerType = LineChartDataModel.PointDrawerType.None
    val labels: List<String> = LineChartHelper.createLineChartLabelsTimestamp(points, step= 8)
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
            pointDrawer = lineChartDataModel.pointDrawer,
            lineChartDataList = lineChartDataTimestampList,
            horizontalOffset = lineChartDataModel.horizontalOffset,
            minValueLabelData = minAndMaxLabels.first.toFloat(),
            maxValueLabelData = minAndMaxLabels.second.toFloat()
        )
    }
}