package com.tradistonks.app.web.repository.room

import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.StrategyItem

class RoomStrategyRepository(private val strategyDatabaseDao: StrategyDatabaseDao) {

    fun getAllStrategies() : List<StrategyItem>? {
        return strategyDatabaseDao.getAll()
    }

    suspend fun addStrategy(strategy: Strategy) {
        strategyDatabaseDao.insert(StrategyItem(strategy))
    }

    fun getStrategyById(id: String): StrategyItem? {
        return strategyDatabaseDao.getById(id)
    }
    suspend fun updateStrategy(strategy: Strategy) {
        strategyDatabaseDao.update(StrategyItem(strategy))
    }
    suspend fun deleteStrategy(strategy: Strategy) {
        strategyDatabaseDao.delete(StrategyItem(strategy))
    }
    suspend fun deleteAllStrategies() {
        strategyDatabaseDao.deleteAllStrategies()
    }
}
