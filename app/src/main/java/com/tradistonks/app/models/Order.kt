package com.tradistonks.app.models

import java.sql.Timestamp

data class Order(val type: String, val symbol: String,
                 var quantity: Double, val timestamp: Long)