package com.tradistonks.app.web.helper

import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.AnnotatedString
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.PREFERENCES
import com.tradistonks.app.components.charts.pie.LegendPosition
import com.tradistonks.app.components.charts.pie.PieChartData
import com.tradistonks.app.components.charts.pie.PieChartEntry
import com.tradistonks.app.components.charts.sample.SimpleColors
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.responses.TokenResponse
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
            val total = data.sum()
            return LegendPosition.values().map {
                PieChartData(
                    entries = data.mapIndexed { idx, value ->
                        PieChartEntry(
                            value = value/total*100,
                            label = AnnotatedString(categories[idx])
                        )
                    },
                    colors = SimpleColors,
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
                        .map(Order::quantity).sum().toDouble())
            }
            return results
        }

        fun retrieveLegendOfData(orders: List<Order>): List<String> {
            return orders.stream().map(Order::symbol).collect(Collectors.toList())
        }
    }
}