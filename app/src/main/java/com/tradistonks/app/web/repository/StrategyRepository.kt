package com.tradistonks.app.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.services.strategy.StrategyService
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

    fun retrieveAllStrategiesOfCurrentUser(token: String, callback: Callback<List<Strategy>>) {
        val call = apiService?.retrieveAllStrategiesOfCurrentUser(token)
        call?.enqueue(callback)
    }

    fun runStrategyById(token: String, idStrategy: String, callback: Callback<JsonObject>){
        val call = apiService?.runStrategyById(token, idStrategy)
        call?.enqueue(callback)
    }

}