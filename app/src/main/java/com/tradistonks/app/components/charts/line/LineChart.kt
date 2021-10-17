package com.tradistonks.app.components.charts.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateDrawableArea
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateFillPath
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateLinePath
import com.tradistonks.app.components.charts.line.LineChartUtils.calculatePointLocation
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateXAxisDrawableArea
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.tradistonks.app.components.charts.line.LineChartUtils.calculateYAxisDrawableArea
import com.tradistonks.app.components.charts.line.LineChartUtils.withProgress
import com.tradistonks.app.components.charts.line.renderer.animation.simpleChartAnimation
import com.tradistonks.app.components.charts.line.renderer.line.LineDrawer
import com.tradistonks.app.components.charts.line.renderer.line.LineShader
import com.tradistonks.app.components.charts.line.renderer.line.NoLineShader
import com.tradistonks.app.components.charts.line.renderer.line.SolidLineDrawer
import com.tradistonks.app.components.charts.line.renderer.point.FilledCircularPointDrawer
import com.tradistonks.app.components.charts.line.renderer.point.PointDrawer
import com.tradistonks.app.components.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.xaxis.XAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.tradistonks.app.components.charts.line.renderer.yaxis.YAxisDrawer

@Composable
fun LineChart(
  colors: List<Color>,
  labels: List<String>,
  allPoints: List<Point>,
  lineChartDataList: List<LineChartData>,
  modifier: Modifier = Modifier,
  animation: AnimationSpec<Float> = simpleChartAnimation(),
  lineShader: LineShader = NoLineShader,
  xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
  yAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
  horizontalOffset: Float = 5f
) {
  check(horizontalOffset in 0f..25f) {
    "Horizontal offset is the % offset from sides, " +
            "and should be between 0%-25%"
  }
  println(allPoints.toString())

  val maxValueCanvas: Float = lineChartDataList.map(LineChartData::maxYValue).sortedDescending()[0]
  val minValueCanvas:Float = lineChartDataList.map(LineChartData::minYValue).sortedDescending().reversed()[0]

  val ListTransitionAnimation: ArrayList<Animatable<Float, AnimationVector1D>> = ArrayList()
    for(LineChartData in lineChartDataList){
      ListTransitionAnimation.add(remember(LineChartData.points) { Animatable(initialValue = 0f) })
    }

  for(element in ListTransitionAnimation){
    LaunchedEffect(allPoints) {
      element.snapTo(0f)
      element.animateTo(1f, animationSpec = animation)
    }
  }



  Canvas(modifier = modifier.fillMaxSize()) {
    drawIntoCanvas { canvas ->
      for (index in lineChartDataList.indices) {
        val color = colors[index]
        val pointDrawer: PointDrawer = FilledCircularPointDrawer(color = color)
        val minValueData = lineChartDataList[0].minYValue
        val maxValueData = lineChartDataList[0].maxYValue

      val yAxisDrawableArea = calculateYAxisDrawableArea(
        xAxisLabelSize = xAxisDrawer.requiredHeight(this),
        size = size
      )
      val xAxisDrawableArea = calculateXAxisDrawableArea(
        yAxisWidth = yAxisDrawableArea.width,
        labelHeight = xAxisDrawer.requiredHeight(this),
        size = size
      )
      val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        offset = horizontalOffset
      )
      val chartDrawableArea = calculateDrawableArea(
        xAxisDrawableArea = xAxisDrawableArea,
        yAxisDrawableArea = yAxisDrawableArea,
        size = size,
        offset = horizontalOffset
      )
      // Draw the X Axis line.
      xAxisDrawer.drawAxisLine(
        drawScope = this,
        drawableArea = xAxisDrawableArea,
        canvas = canvas
      )

      xAxisDrawer.drawAxisLabels(
        drawScope = this,
        canvas = canvas,
        drawableArea = xAxisLabelsDrawableArea,
        labels = labels
      )

      // Draw the Y Axis line.
      yAxisDrawer.drawAxisLine(
        drawScope = this,
        canvas = canvas,
        drawableArea = yAxisDrawableArea
      )


      yAxisDrawer.drawAxisLabels(
        drawScope = this,
        canvas = canvas,
        drawableArea = yAxisDrawableArea,
        minValue = minValueCanvas,
        maxValue = maxValueCanvas
      )

        val lineDrawer: LineDrawer = SolidLineDrawer(color = color)
        // Draw the chart line.
        lineDrawer.drawLine(
          drawScope = this,
          canvas = canvas,
          linePath = calculateLinePath(
            drawableArea = chartDrawableArea,
            lineChartData = lineChartDataList[index],
            transitionProgress = ListTransitionAnimation[index].value,
            minValueData = lineChartDataList[0].minYValue,
            maxValueData = lineChartDataList[0].maxYValue,
            maxValueCanvas = maxValueCanvas,
            minValueCanvas = minValueCanvas,
            totalNbLabels = labels.count()
          )
        )

        lineShader.fillLine(
          drawScope = this,
          canvas = canvas,
          fillPath = calculateFillPath(
            drawableArea = chartDrawableArea,
            lineChartData = lineChartDataList[index],
            transitionProgress = ListTransitionAnimation[index].value,
            minValueData = lineChartDataList[0].minYValue,
            maxValueData = lineChartDataList[0].maxYValue,
            maxValueCanvas = maxValueCanvas,
            minValueCanvas = minValueCanvas,
            totalNbLabels = labels.count()
          )
        )

        lineChartDataList[index].points.forEachIndexed { idx, point ->
          withProgress(
            index = idx,
            lineChartData = lineChartDataList[index],
            transitionProgress = ListTransitionAnimation[index].value
          ) {
            pointDrawer.drawPoint(
              drawScope = this,
              canvas = canvas,
              center = calculatePointLocation(
                drawableArea = chartDrawableArea,
                lineChartData = lineChartDataList[index],
                point = point,
                index = idx,
                minValueData = lineChartDataList[0].minYValue,
                maxValueData = lineChartDataList[0].maxYValue,
                maxValueCanvas = maxValueCanvas,
                minValueCanvas = minValueCanvas,
                totalNbLabels = labels.count()
              )
            )
          }
        }
      }

    }
  }
}
