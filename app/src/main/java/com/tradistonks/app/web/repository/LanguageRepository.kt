package com.tradistonks.app.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.Language
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.web.services.language.LanguageService
import com.tradistonks.app.web.services.strategy.StrategyService
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LanguageRepository {
    private var apiService: LanguageService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.TRADISTONKSAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(LanguageService::class.java)
    }

    fun retrieveAllLanguagesOfUser(token: String, callback: Callback<List<Language>>) {
        val call = apiService?.retrieveAllLanguagesOfUser(token)
        call?.enqueue(callback)
    }

}