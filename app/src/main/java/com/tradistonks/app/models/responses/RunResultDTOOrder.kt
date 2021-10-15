package com.tradistonks.app.models.responses

data class RunResultDTOOrder(
    val type: String,
    val symbol: String,
    val quantity: Number,
    val timestamp: Number,
)