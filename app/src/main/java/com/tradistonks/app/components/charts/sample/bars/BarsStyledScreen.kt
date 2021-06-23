package com.tradistonks.app.components.charts.sample.bars

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
import androidx.compose.ui.unit.dp
import com.tradistonks.app.components.charts.Container
import com.tradistonks.app.components.charts.ScreenContainer
import com.tradistonks.app.components.charts.bars.HorizontalBarsChart
import com.tradistonks.app.components.charts.bars.data.HorizontalBarsData
import com.tradistonks.app.components.charts.sample.BarsSampleData

@Composable
fun BarsStyledScreenContainer() {
  ScreenContainer {
    items(BarsSampleData) { (title, data) ->
      Container(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
          .padding(16.dp)
          .animateContentSize(),
        title = title
      ) {
        HorizontalBarsChart(
          data = data,
          legendOffset = 12.dp,
          divider = {
            Divider(color = Color.LightGray)
          },
          textContent = {
            Text(it, style = MaterialTheme.typography.subtitle1)
          },
          valueContent = {
            Text(it, style = MaterialTheme.typography.caption)
          },
        )
      }
    }
  }
}


@Composable
fun BarsStyled(title: String, data: HorizontalBarsData) {
    Container(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
        .padding(16.dp)
        .animateContentSize(),
      title = title
    ) {
      HorizontalBarsChart(
        data = data,
        legendOffset = 12.dp,
        divider = {
          Divider(color = Color.LightGray)
        },
        textContent = {
          Text(it, style = MaterialTheme.typography.subtitle1)
        },
        valueContent = {
          Text(it, style = MaterialTheme.typography.caption)
        },
      )
    }
}
