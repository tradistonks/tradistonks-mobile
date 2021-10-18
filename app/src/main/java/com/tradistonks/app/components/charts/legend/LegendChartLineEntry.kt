package com.tradistonks.app.components.charts.legend

import com.tradistonks.app.components.charts.ChartShape

data class LegendChartLineEntry(
  val text: String,
  val shape: ChartShape = ChartShape.Default
)
