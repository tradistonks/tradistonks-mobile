package com.tradistonks.app.repository

import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.services.strategy.StrategyService
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StrategyRepository {
    private var apiService: StrategyService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.TRADISTONKSAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(StrategyService::class.java)
    }

    fun retrieveAllStrategiesOfUser(callback: Callback<List<Strategy>>) {
        val call = apiService?.retrieveAllStrategiesOfUser()
        call?.enqueue(callback)
    }
}