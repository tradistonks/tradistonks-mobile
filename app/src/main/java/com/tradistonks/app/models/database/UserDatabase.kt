package com.tradistonks.app.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDatabase(
    @PrimaryKey
    val username: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "roles")
    var roles: List<String>,
    @ColumnInfo(name = "token")
    var token: String
)