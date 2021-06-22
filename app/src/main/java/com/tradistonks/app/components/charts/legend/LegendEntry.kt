package com.tradistonks.app.components.charts.legend

import androidx.compose.ui.text.AnnotatedString
import com.tradistonks.app.components.charts.ChartShape

data class LegendEntry(
  val text: AnnotatedString,
  val value: Float,
  val percent: Float = Float.MAX_VALUE,
  val shape: ChartShape = ChartShape.Default
)
