package com.tradistonks.app.database

import androidx.room.TypeConverter
import java.sql.Date

class DateConverter {

    @TypeConverter
    fun toDate(value:Long?):Date{
        val date = Date(value!!*1000L)

        return date
    }

    @TypeConverter
    fun fromDate(date: Date?):Long{
        return date!!.time
    }
}