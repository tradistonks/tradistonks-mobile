package com.tradistonks.app.models.responses

data class RunResultDTOPhase(
    val name: String,
    val status: Number,
    val stdout: String,
    val stderr: String,
    val time: Number,
    val time_wall: Number,
    val used_memory: Number,
    val sandbox_status: String?,
    val csw_voluntary: Number,
    val csw_forced: Number,
)