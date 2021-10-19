package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.StrategyItem

class RoomStrategyRepository(private val strategyDatabaseDao: StrategyDatabaseDao) {
    val readAllData : LiveData<List<StrategyItem>> =  strategyDatabaseDao.getAll()

    suspend fun addStrategy(strategy: StrategyItem) {
        strategyDatabaseDao.insert(strategy)
    }
    suspend fun updateStrategy(strategy: StrategyItem) {
        strategyDatabaseDao.update(strategy)
    }
    suspend fun deleteStrategy(strategy: StrategyItem) {
        strategyDatabaseDao.delete(strategy)
    }
    suspend fun deleteAllStrategies() {
        strategyDatabaseDao.deleteAllStrategies()
    }
}
