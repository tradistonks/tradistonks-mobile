package com.tradistonks.app.services.strategy

import com.tradistonks.app.models.Strategy
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import retrofit2.http.GET

interface StrategyService {

    @GET("strategies")
    fun retrieveAllStrategiesOfUser(): Call<List<Strategy>>

    //run strategies:strategy_id/run run a strategy from its id

    //me/strategies = strategies of the user

}