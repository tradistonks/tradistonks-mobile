package com.tradistonks.app.web.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tradistonks.app.models.database.StrategyItem
import com.tradistonks.app.models.database.UserItem

@Database(entities = [UserItem::class, StrategyItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDatabaseDao
    abstract fun strategyDao(): UserDatabaseDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "tradistonks-database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}