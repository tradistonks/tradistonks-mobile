package com.tradistonks.app.models.responses

import com.tradistonks.app.models.Order

data class RunResultDto(
    val phases: List<RunResultDTOPhase>,
    val orders: List<Order>?,
    val history: Pair<Number, Pair<String, RunResultDTOHistoryCandle>>?,
    val config: RunResultDTOConfig?,
    val pnl: Pair<Number, Pair<String, Number>>?)
