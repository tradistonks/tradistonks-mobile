package com.tradistonks.app.web.repository.room

import androidx.lifecycle.LiveData
import com.tradistonks.app.models.Strategy

class RoomStrategyRepository(private val strategyDatabaseDao: StrategyDatabaseDao) {
    val readAllData : LiveData<List<Strategy>> =  strategyDatabaseDao.getAll()

    suspend fun addStrategy(strategy: Strategy) {
        strategyDatabaseDao.insert(strategy)
    }
    suspend fun updateStrategy(strategy: Strategy) {
        strategyDatabaseDao.update(strategy)
    }
    suspend fun deleteStrategy(strategy: Strategy) {
        strategyDatabaseDao.delete(strategy)
    }
    suspend fun deleteAllStrategies() {
        strategyDatabaseDao.deleteAllStrategies()
    }
}
