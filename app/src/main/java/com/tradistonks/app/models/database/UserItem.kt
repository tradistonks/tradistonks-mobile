package com.tradistonks.app.models.database

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tradistonks.app.models.responses.auth.UserResponse
import com.tradistonks.app.web.helper.database.Converters

@Entity(tableName = "users")
data class UserItem(
    @PrimaryKey
    val _id:String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    var email: String,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "created_date")
    val created_date: Long?,

    @ColumnInfo(name = "roles")
    @TypeConverters(Converters::class)
    var roles: String,

    @ColumnInfo(name = "token")
    var token: String
){
    constructor(user: UserResponse) : this(
        user._id, user.username, user.email,
        Converters.fromDateNullable(user.created_date), Converters.fromStringList(user.roles!!), user.token!!)

    companion object{
        fun toUserResponse(userItem: UserItem) = UserResponse(
            userItem._id, userItem.username, userItem.email, Converters.toDateNullable(userItem.created_date),
            Converters.toStringList(userItem.roles), userItem.token
        )
    }
}