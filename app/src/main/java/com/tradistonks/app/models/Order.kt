package com.tradistonks.app.models

import java.sql.Timestamp

data class Order(val type: String, val symbol: String,
                    val quantity: Int, val timestamp: Long)