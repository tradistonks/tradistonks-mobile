package com.tradistonks.app.web.services.strategy

import com.google.gson.JsonObject
import com.tradistonks.app.models.responses.strategy.StrategyResponse
import retrofit2.Call
import retrofit2.http.*

interface StrategyService {

    @GET("/users/me/strategies")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun retrieveAllStrategiesOfCurrentUser(@Header("Cookie") token: String): Call<List<StrategyResponse>>

    @POST("/strategies/{idStrategy}/run")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun runStrategyById(@Header("Cookie") token: String, @Path("idStrategy") id: String): Call<JsonObject>

}