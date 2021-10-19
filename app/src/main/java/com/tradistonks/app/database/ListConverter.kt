package com.tradistonks.app.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.models.responses.strategy.RunResultDto

class ListConverter {
    @TypeConverter
    fun fromStringList(results: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(results, type)
    }
    @TypeConverter
    fun toStringList(results: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(results, type)
    }
}