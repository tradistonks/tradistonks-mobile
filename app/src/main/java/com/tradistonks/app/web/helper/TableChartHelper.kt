package com.tradistonks.app.web.helper

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.tradistonks.app.components.charts.ChartShape
import com.tradistonks.app.components.charts.table.TableEntry
import com.tradistonks.app.ui.theme.colors
import kotlin.random.Random

class TableChartHelper {

    companion object {
        fun retrieveTableChartData(categories: List<String>, data: List<Double>): List<Pair<String, List<TableEntry>>> {
                return listOf(
                "Without Shape" to (categories.indices).map
                {
                    TableEntry(
                        key = AnnotatedString(categories[it]),
                        value = AnnotatedString(data[it].toString()),
                    )
                },
                "With Shape" to (categories.indices).map
                {
                    TableEntry(
                        key = AnnotatedString(categories[it]),
                        value = AnnotatedString(data[it].toString()),
                        drawShape = ChartShape(
                            size = 8.dp,
                            shape = CircleShape,
                            color = colors[it],
                        )
                    )
                },
                )
            }
    }
}
