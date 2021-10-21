package com.tradistonks.app.models.database

import androidx.annotation.Nullable
import androidx.room.*
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.helper.database.Converters

@Entity(tableName = "strategies")
data class StrategyItem(
    @PrimaryKey
    val _id: String,

    @ColumnInfo(name = "user")
    var user: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "language")
    var language: String,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "from")
    var from: Long?,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "to")
    var to: Long?,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "updated_date")
    var updated_date: Long?,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "created_date")
    var created_date: Long?,


    @TypeConverters(Converters::class)
    @ColumnInfo(name = "loading")
    var loading: Boolean,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "last_run")
    var last_run: Long?,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "hasResults")
    var hasResults: Boolean,

    @TypeConverters(Converters::class)
    @Nullable
    @ColumnInfo(name = "results")
    var results: String?

) {
    constructor(strategy: Strategy) : this(
        _id = strategy._id, user = strategy.user, name = strategy.name,
        language = strategy.language, from = Converters.fromDateNullable(strategy.from),
        to = Converters.fromDateNullable(strategy.to),
        updated_date = Converters.fromDateNullable(strategy.updated_date),
        created_date =Converters.fromDateNullable(strategy.created_date),
        loading =Converters.toBoolean(strategy.loading),
        last_run = Converters.fromDateNullable(strategy.last_run)!!,
        hasResults =Converters.toBoolean(strategy.hasResults),
        results =Converters.fromResultsList(strategy.results)
    )


    companion object {
        fun toStrategy(strategyItem: StrategyItem?) : Strategy? {
            if(strategyItem != null){
                return Strategy(strategyItem._id, strategyItem.user, strategyItem.name, strategyItem.language,
                    Converters.toDateNullable(strategyItem.from),
                    Converters.toDateNullable(strategyItem.to),
                    Converters.toDateNullable(strategyItem.updated_date),
                    Converters.toDateNullable(strategyItem.created_date),
                    Converters.toMutableBoolean(strategyItem.loading),
                    Converters.toDateNullable(strategyItem.last_run),
                    Converters.toMutableBoolean(strategyItem.hasResults),
                    Converters.toResultsList(strategyItem.results!!)
                )
            }else{
                return null
            }
        }

    }
}
