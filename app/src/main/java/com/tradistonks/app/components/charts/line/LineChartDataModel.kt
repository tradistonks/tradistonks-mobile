package com.tradistonks.app.components.charts.line
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tradistonks.app.components.charts.line.renderer.point.FilledCircularPointDrawer
import com.tradistonks.app.components.charts.line.renderer.point.HollowCircularPointDrawer
import com.tradistonks.app.components.charts.line.renderer.point.NoPointDrawer
import com.tradistonks.app.components.charts.line.renderer.point.PointDrawer
import com.tradistonks.app.ui.theme.colorRed

class LineChartDataModel {
    var horizontalOffset by mutableStateOf(5f)
    var pointDrawerType by mutableStateOf(PointDrawerType.Filled)
    val pointDrawer: PointDrawer
        get() {
            return when (pointDrawerType) {
                PointDrawerType.None -> NoPointDrawer
                PointDrawerType.Filled -> FilledCircularPointDrawer(color = colorRed)
                PointDrawerType.Hollow -> HollowCircularPointDrawer()
            }
        }

    private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f

    enum class PointDrawerType {
        None,
        Filled,
        Hollow
    }
}