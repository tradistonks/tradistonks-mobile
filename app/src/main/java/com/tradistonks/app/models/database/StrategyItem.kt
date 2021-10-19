package com.tradistonks.app.models.database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.database.ResultsConverter
import com.tradistonks.app.models.responses.strategy.RunResultDto
import java.util.*

@Entity(tableName = "strategies")
data class StrategyItem(
    @PrimaryKey
    val _id: String,
    @ColumnInfo(name = "user")
    var user: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "language")
    var language: String,
    @ColumnInfo(name = "from")
    var from: Date,
    @ColumnInfo(name = "to")
    var to: Date?,
    @ColumnInfo(name = "from")
    var updated_date: Date,
    @ColumnInfo(name = "to")
    var created_date: Date,
    @ColumnInfo(name = "loading")
    var loading: MutableState<Boolean>,
    @ColumnInfo(name = "last_run")
    var last_run: Date?,
    @ColumnInfo(name = "hasResults")
    var hasResults: MutableState<Boolean>,
    @TypeConverters(ResultsConverter::class)
    @ColumnInfo(name = "results")
    var results: RunResultDto?

)