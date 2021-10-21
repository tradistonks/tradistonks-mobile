package com.tradistonks.app.models.responses.strategy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tradistonks.app.models.Strategy
import java.util.*

data class StrategySerializable(val _id: String, val user: String, val name: String,
                                val language: String, val from: Date?, val to: Date? = null,
                                val updated_date: Date?, val created_date: Date?,
                                var loading: Boolean,
                                var last_run: Date? = Date(),
                                var hasResults: Boolean,
                                var results: RunResultDto?) {

    fun toStrategy(): Strategy{
        return Strategy(this._id, this.user, this.name,
        this.language, this.from, this.to,
        this.updated_date, this.created_date,
        mutableStateOf(this.loading),
        this.last_run,
            mutableStateOf(this.hasResults),
        this.results)
    }
}