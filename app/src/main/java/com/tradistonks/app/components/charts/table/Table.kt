package com.tradistonks.app.components.charts.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.tradistonks.app.components.charts.internal.DefaultText

@Deprecated(
  "Use TableChart instead",
  ReplaceWith("TableChart(data, modifier, shapeModifier, keyText, valueText, divider)")
)
@Composable
fun Table(
  data: List<TableEntry>,
  modifier: Modifier = Modifier,
  shapeModifier: Modifier = Modifier.requiredSize(8.dp),
  keyText: @Composable RowScope.(key: AnnotatedString?) -> Unit = { DefaultText(text = it) },
  valueText: @Composable RowScope.(value: AnnotatedString?) -> Unit = { DefaultText(text = it) },
  divider: @Composable (() -> Unit)? = null,
) {
  TableChart(data, modifier, shapeModifier, keyText, valueText, divider)
}

@Composable
fun TableChart(
  data: List<TableEntry>,
  modifier: Modifier = Modifier,
  shapeModifier: Modifier = Modifier.requiredSize(8.dp),
  keyText: @Composable RowScope.(key: AnnotatedString?) -> Unit = { DefaultText(text = it) },
  valueText: @Composable RowScope.(value: AnnotatedString?) -> Unit = { DefaultText(text = it) },
  divider: @Composable (() -> Unit)? = null,
) {
  Column(modifier) {
    data.forEachIndexed { idx, item ->
      TableRow(
        entry = item,
        shapeModifier = shapeModifier,
        keyText = keyText,
        valueText = valueText,
      )
      if (idx != data.lastIndex && divider != null)
        divider()
    }
  }
}
