package com.tradistonks.app.web.services.strategy

import android.util.Log
import androidx.navigation.NavHostController
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.TokenResponse
import com.tradistonks.app.repository.StrategyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StrategyController{
    fun retrieveAllStrategiesOfCurrentUser(token: TokenResponse, navController: NavHostController) {
        StrategyRepository.retrieveAllStrategiesOfCurrentUser(token.token, object : Callback<List<Strategy>> {
            override fun onFailure(call: Call<List<Strategy>>, t: Throwable) {
                Log.d("tradistonks-strategies", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Strategy>>,
                response: Response<List<Strategy>>
            ) {
                Log.d(
                    "tradistonks-strategies",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}}"
                )
            }
        })
    }

    fun runStrategyById(token: TokenResponse, idStrategy: String, navController: NavHostController) {
        StrategyRepository.runStrategyById(token.token, idStrategy, object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d(
                    "tradistonks-run",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}}"
                )
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("tradistonks-run", "Error : ${t.message}")
            }

        })
    }


}
