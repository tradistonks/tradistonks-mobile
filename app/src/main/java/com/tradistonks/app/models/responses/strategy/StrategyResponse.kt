package com.tradistonks.app.models.responses.strategy

import androidx.compose.runtime.mutableStateOf
import com.tradistonks.app.models.Strategy
import java.util.*

data class StrategyResponse(
    val _id: String, val user: String, val name: String,
    val language: String, val from: Date, val to: Date? = null,
    val updated_date: Date, val created_date: Date
){

    fun toStrategy(): Strategy {
        return Strategy(_id, user, name, language, from,
            to, updated_date, created_date,
            loading= mutableStateOf(false), last_run = Date(),
            hasResults = mutableStateOf(false), results = null)
    }
}