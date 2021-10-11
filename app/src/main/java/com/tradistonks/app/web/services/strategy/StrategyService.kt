package com.tradistonks.app.web.services.strategy

import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface StrategyService {

    @GET("/me/strategies")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun retrieveAllStrategiesOfCurrentUser(@Header("Cookie") token: String): Call<List<Strategy>>

    @POST("/strategies/{idStrategy}/run")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun runStrategyById(@Header("Cookie") token: String, @Path("idStrategy") id: String): Call<Void>

}