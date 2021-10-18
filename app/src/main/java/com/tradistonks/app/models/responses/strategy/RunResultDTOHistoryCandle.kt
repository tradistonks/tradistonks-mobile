package com.tradistonks.app.models.responses.strategy

data class RunResultDTOHistoryCandle(
    val open: Number,
    val high: Number,
    val low: Number,
    val close: Number,
    val volume: Number,
    val timestamp: Number,
)