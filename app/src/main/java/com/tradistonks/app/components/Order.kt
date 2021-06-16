package com.tradistonks.app.components

import java.util.*

data class Order(val type: String, val symbol: String, val price: Float,
                    val quantity: Int, val created_date: Date){
}