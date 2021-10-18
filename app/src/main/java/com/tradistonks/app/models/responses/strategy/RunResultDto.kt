package com.tradistonks.app.models.responses.strategy

import com.tradistonks.app.models.Order

data class RunResultDto(
    val phases: List<RunResultDTOPhase>,
    val orders: List<Order>?,
    val history: Map<Number, Map<String, RunResultDTOHistoryCandle>>?,
    val config: RunResultDTOConfig?,
    val pnl: Map<Number, Map<String, Number>>?)
