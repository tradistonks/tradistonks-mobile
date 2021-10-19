package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tradistonks.app.models.Strategy

@Dao
interface StrategyDatabaseDao {
    @Query("SELECT * from strategies")
    fun getAll(): LiveData<List<Strategy>>
    @Query("SELECT * from strategies where _id = :id")
    fun getById(id: String) : Strategy?
    @Insert
    suspend fun insert(item:Strategy)
    @Update
    suspend fun update(item:Strategy)
    @Delete
    suspend fun delete(item:Strategy)
    @Query("DELETE FROM strategies")
    suspend fun deleteAllStrategies()
}