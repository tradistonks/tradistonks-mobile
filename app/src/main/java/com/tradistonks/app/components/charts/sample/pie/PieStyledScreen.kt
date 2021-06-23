package com.tradistonks.app.components.charts.sample.pie

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tradistonks.app.components.charts.Container
import com.tradistonks.app.components.charts.ScreenContainer
import com.tradistonks.app.components.charts.legend.LegendEntry
import com.tradistonks.app.components.charts.pie.PieChart
import com.tradistonks.app.components.charts.pie.PieChartData
import com.tradistonks.app.components.charts.sample.PieSampleData
import com.tradistonks.app.components.charts.sample.buildValuePercentString

@Composable
fun PieStyledScreenContainer() {
  ScreenContainer {
    items(PieSampleData) { data ->
      Container(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
          .padding(16.dp)
          .animateContentSize(),
        title = "Pie - Legend::${data.legendPosition.name}"
      ) {
        PieChart(
          data = data,
          legend = { entries ->
            CustomVerticalLegend(entries = entries)
          }
        )
      }
    }
  }
}


@Composable
fun PieStyledScreen(data: PieChartData) {
    Container(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
        .padding(16.dp)
        .animateContentSize(),
      title = "Pie - Legend::${data.legendPosition.name}"
    ) {
      PieChart(
        data = data,
        legend = { entries ->
          CustomVerticalLegend(entries = entries)
        }
      )
    }
}


@Composable
private fun RowScope.CustomVerticalLegend(entries: List<LegendEntry>) {
  Column(
    modifier = Modifier.Companion.weight(1f),
  ) {
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
          style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = buildValuePercentString(item),
          style = MaterialTheme.typography.caption,
        )
      }

      if (idx != entries.lastIndex)
        Divider()
    }
  }
}
