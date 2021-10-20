package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.UserItem
import com.tradistonks.app.models.responses.auth.UserResponse

@Dao
interface UserDatabaseDao {
    @Query("SELECT * from users")
    fun getAll(): List<UserItem>?
    @Query("SELECT * from users where _id = :id")
    fun getById(id: String) : UserItem?
    @Insert
    suspend fun insert(item:UserItem)
    @Update
    suspend fun update(item:UserItem)
    @Delete
    suspend fun delete(item:UserItem)
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}