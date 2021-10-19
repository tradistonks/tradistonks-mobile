package com.tradistonks.app.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tradistonks.app.database.ListConverter
import com.tradistonks.app.database.ResultsConverter

@Entity(tableName = "users")
data class UserItem(
    @PrimaryKey
    val id:String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "roles")
    @TypeConverters(ListConverter::class)
    var roles: List<String>,

    @ColumnInfo(name = "token")
    var token: String
)