package com.tradistonks.app.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tradistonks.app.database.DateConverter
import com.tradistonks.app.database.ListConverter
import com.tradistonks.app.database.ResultsConverter
import java.util.*

@Entity(tableName = "users")
data class UserItem(
    @PrimaryKey
    val _id:String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    var email: String,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "created_date")
    val created_date: String,

    @ColumnInfo(name = "roles")
    @TypeConverters(ListConverter::class)
    var roles: String,

    @ColumnInfo(name = "token")
    var token: String
)