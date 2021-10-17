package com.tradistonks.app.web.helper

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.AnnotatedString
import com.tradistonks.app.components.charts.pie.LegendPosition
import com.tradistonks.app.components.charts.pie.PieChartData
import com.tradistonks.app.components.charts.pie.PieChartEntry
import com.tradistonks.app.models.Order
import com.tradistonks.app.ui.theme.colors
import java.util.stream.Collectors

class PieChartHelper {
    companion object {
        fun retrievePieChartData(orders: List<Order>): Map<String, List<PieChartData>> {
            val categories = retrieveLegendOfData(orders)
            val buy = retrievePieChartDataByType(orders, categories, "Buy")
            val sell = retrievePieChartDataByType(orders, categories, "Sell")
            return mapOf("Buy" to buy, "Sell" to sell)
        }

        fun retrievePieChartDataByType(orders: List<Order>, categories: List<String>, type: String): List<PieChartData>{
            val data = retrieveSumsOfTypeBySymbol(orders, categories, type)
            return LegendPosition.values().map {
                PieChartData(
                    entries = data.mapIndexed { idx, value ->
                        PieChartEntry(
                            value = value.toFloat(),
                            label = AnnotatedString(categories[idx])
                        )
                    },
                    colors = colors,
                    legendPosition = it,
                    legendShape = CircleShape,
                )
            }
        }

        private fun retrieveSumsOfTypeBySymbol(orders: List<Order>, categories: List<String>, type: String): List<Double> {
            val results: MutableList<Double> = mutableListOf()
            for (category in categories){
                results.add(
                    orders
                        .filter { order ->
                            order.type == type }
                        .filter { order ->
                            order.symbol == category}
                        .map(Order::quantity).sum())
            }
            return results
        }

        fun retrieveLegendOfData(orders: List<Order>): List<String> {
            return orders.stream().map(Order::symbol).distinct()
                .collect(Collectors.toList())
        }
    }
}