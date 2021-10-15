package com.tradistonks.app.models.responses

data class RunResultDto(
    val phases: List<RunResultDTOPhase>,
    val orders: List<RunResultDTOOrder>?,
    val history: Pair<Number, Pair<String, RunResultDTOHistoryCandle>>?,
    val config: RunResultDTOConfig?,
    val pnl: Pair<Number, Pair<String, Number>>?)
