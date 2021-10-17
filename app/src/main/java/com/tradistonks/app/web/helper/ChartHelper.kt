package com.tradistonks.app.web.helper

import com.tradistonks.app.models.Order
import java.util.stream.Collectors

class ChartHelper {
    companion object {

        fun retrieveSumsOfTypeBySymbol(
            orders: List<Order>,
            categories: List<String>,
            type: String
        ): List<Double> {
            val results: MutableList<Double> = mutableListOf()
            for (category in categories) {
                results.add(
                    orders
                        .filter { order ->
                            order.type == type
                        }
                        .filter { order ->
                            order.symbol == category
                        }
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