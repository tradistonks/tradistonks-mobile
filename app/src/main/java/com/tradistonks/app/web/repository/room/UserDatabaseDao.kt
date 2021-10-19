package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.auth.UserResponse

@Dao
interface UserDatabaseDao {
    @Query("SELECT * from users")
    fun getAll(): LiveData<List<UserResponse>>
    @Query("SELECT * from users where id = :id")
    fun getById(id: String) : UserResponse?
    @Insert
    suspend fun insert(item:UserResponse)
    @Update
    suspend fun update(item:UserResponse)
    @Delete
    suspend fun delete(item:UserResponse)
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}