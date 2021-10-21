package com.tradistonks.app.web.helper.database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.models.responses.strategy.RunResultDto
import java.util.*

class Converters {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromDateNullable(date: Date?): Long? {
            if (date != null) {
                return date.time
            }else{
                return null
            }
        }

        @TypeConverter
        @JvmStatic
        fun toDateNullable(value: Long?): Date? {
            if (value != null) {
                val date = Date(value * 1000L)
                return date
            }
            return null
        }


        @TypeConverter
        @JvmStatic
        fun fromResultsList(results: RunResultDto?): String {
            val type = object : TypeToken<RunResultDto>() {}.type
            return Gson().toJson(results, type)
        }

        @TypeConverter
        @JvmStatic
        fun toResultsList(results: String): RunResultDto? {
            val type = object : TypeToken<RunResultDto>() {}.type
            return Gson().fromJson<RunResultDto>(results, type)
        }

        @TypeConverter
        @JvmStatic
        fun toMutableBoolean(value: Boolean): MutableState<Boolean> {
            return mutableStateOf(value)
        }

        @TypeConverter
        @JvmStatic
        fun toBoolean(mutableBoolean: MutableState<Boolean>): Boolean {
            return mutableBoolean.value
        }


        @TypeConverter
        @JvmStatic
        fun fromStringList(results: List<String>): String {
            val type = object : TypeToken<List<String>>() {}.type
            return Gson().toJson(results, type)
        }

        @TypeConverter
        @JvmStatic
        fun toStringList(results: String): List<String> {
            val type = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(results, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromDate(date: Date): Long {
            return date.time
        }
    }
}