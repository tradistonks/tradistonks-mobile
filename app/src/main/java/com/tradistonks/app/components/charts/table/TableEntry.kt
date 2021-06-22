package com.tradistonks.app.components.charts.table

import androidx.compose.ui.text.AnnotatedString
import com.tradistonks.app.components.charts.ChartShape

data class TableEntry(
  val key: AnnotatedString?,
  val value: AnnotatedString?,
  val drawShape: ChartShape? = null,
)
