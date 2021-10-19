package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.StrategyItem

@Dao
interface StrategyDatabaseDao {
    @Query("SELECT * from strategies")
    fun getAll(): LiveData<List<StrategyItem>>
    @Query("SELECT * from strategies where _id = :id")
    fun getById(id: String) : StrategyItem?
    @Insert
    suspend fun insert(item:StrategyItem)
    @Update
    suspend fun update(item:StrategyItem)
    @Delete
    suspend fun delete(item:StrategyItem)
    @Query("DELETE FROM strategies")
    suspend fun deleteAllStrategies()
}