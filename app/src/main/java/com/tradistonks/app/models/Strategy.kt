package com.tradistonks.app.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tradistonks.app.models.responses.strategy.RunResultDto
import java.util.*

data class Strategy (
    val _id: String, val user: String, val name: String,
    val language: String, val from: Date, val to: Date? = null,
    val updated_date: Date, val created_date: Date,
    var loading: MutableState<Boolean> = mutableStateOf(false),
    var last_run: Date? = Date(),
    var hasResults: MutableState<Boolean> = mutableStateOf(false),
    var results: RunResultDto?)
