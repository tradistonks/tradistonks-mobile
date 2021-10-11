package com.tradistonks.app.web.services.strategy

import android.util.Log
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.TOKEN
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.TokenResponse
import com.tradistonks.app.models.responses.UserResponse
import com.tradistonks.app.repository.StrategyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StrategyController{
    var strategies: List<Strategy>? = null

    fun retrieveAllStrategiesOfCurrentUser(tokenResponse: TokenResponse, navController: NavHostController) {
        StrategyRepository.retrieveAllStrategiesOfCurrentUser(TOKEN, object : Callback<List<Strategy>>{
            override fun onFailure(call: Call<List<Strategy>>, t: Throwable) {
                Log.d("tradistonks-strategies", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Strategy>>,
                response: Response<List<Strategy>>
            ) {
                strategies = response.body()
                Log.d(
                    "tradistonks-strategies",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}, json = $strategies"
                )
                navController.navigate("strategies")
            }
        })
    }

    fun runStrategyById(tokenResponse: TokenResponse, idStrategy: String) {
        StrategyRepository.runStrategyById(TOKEN, idStrategy, object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(
                    "tradistonks-run",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}}"
                )
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("tradistonks-run", "Error : ${t.message}")
            }

        })
    }


}
