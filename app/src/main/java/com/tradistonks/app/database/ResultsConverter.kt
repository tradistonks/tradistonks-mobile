package com.tradistonks.app.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.models.responses.strategy.RunResultDto

class ResultsConverter {
    @TypeConverter
    fun fromResultsList(results: RunResultDto?): String? {
        val type = object : TypeToken<RunResultDto>() {}.type
        return Gson().toJson(results, type)
    }
    @TypeConverter
    fun toResultsList(results: String?): RunResultDto? {
        val type = object : TypeToken<RunResultDto>() {}.type
        return Gson().fromJson<RunResultDto>(results, type)
    }
}