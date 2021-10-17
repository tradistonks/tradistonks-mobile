package com.tradistonks.app.components.charts.sample.table

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradistonks.app.components.charts.Container
import com.tradistonks.app.components.charts.ScreenContainer
import com.tradistonks.app.components.charts.sample.TableSampleData
import com.tradistonks.app.components.charts.table.TableChart
import com.tradistonks.app.components.charts.table.TableEntry

@Composable
fun TableStyledScreenContainer() {
  ScreenContainer {
    items(TableSampleData) { (title, entries) ->
      Container(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
          .padding(16.dp)
          .animateContentSize(),
        title = title
      ) {
        TableChart(
          modifier = Modifier.fillMaxWidth(),
          data = entries,
          keyText = { key ->
            Text(
              text = key ?: AnnotatedString("Placeholder"),
              style = MaterialTheme.typography.caption.copy(fontSize = 14.sp),
            )
          },
          valueText = { value ->
            Text(
              text = value ?: AnnotatedString("99"),
              style = MaterialTheme.typography.body2,
            )
          },
          divider = {
            Divider(modifier = Modifier.padding(vertical = 14.dp))
          },
        )
      }
    }
  }
}


@Composable
fun TableStyledScreen(table: Pair<String, List<TableEntry>>, title: String) {
  Container(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
      .padding(16.dp)
      .animateContentSize(),
    title = title
  ) {
    TableChart(
      modifier = Modifier.fillMaxWidth(),
      data = table.second,
      keyText = { key ->
        Text(
          text = key ?: AnnotatedString("Placeholder"),
          style = MaterialTheme.typography.caption.copy(fontSize = 14.sp),
        )
      },
      valueText = { value ->
        Text(
          text = value ?: AnnotatedString("99"),
          style = MaterialTheme.typography.body2,
        )
      },
      divider = {
        Divider(modifier = Modifier.padding(vertical = 14.dp))
      },
    )
  }
}
