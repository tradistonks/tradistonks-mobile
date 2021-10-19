package com.tradistonks.app.database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter
import java.sql.Date

class MutableBooleanConverter {
    @TypeConverter
    fun toMutableBoolean(value:Boolean): MutableState<Boolean> {
        return mutableStateOf(value)
    }

    @TypeConverter
    fun toBoolean(mutableBoolean: MutableState<Boolean>): Boolean{
        return mutableBoolean.value
    }
}