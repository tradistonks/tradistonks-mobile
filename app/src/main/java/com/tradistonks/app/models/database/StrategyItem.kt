package com.tradistonks.app.models.database

import androidx.annotation.Nullable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.database.DateConverter
import com.tradistonks.app.database.MutableBooleanConverter
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

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "from")
    var from: Long,

    @TypeConverters(DateConverter::class)
    @Nullable
    @ColumnInfo(name = "to")
    var to: Long,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "updated_date")
    var updated_date: Long,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "created_date")
    var created_date: Long,


    @TypeConverters(MutableBooleanConverter::class)
    @ColumnInfo(name = "loading")
    var loading: Boolean,

    @TypeConverters(DateConverter::class)
    @Nullable
    @ColumnInfo(name = "last_run")
    var last_run: Long,

    @TypeConverters(MutableBooleanConverter::class)
    @ColumnInfo(name = "hasResults")
    var hasResults: Boolean,

    @TypeConverters(ResultsConverter::class)
    @Nullable
    @ColumnInfo(name = "results")
    var results: String

)
