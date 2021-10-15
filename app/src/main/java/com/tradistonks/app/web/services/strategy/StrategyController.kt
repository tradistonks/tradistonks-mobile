package com.tradistonks.app.web.services.strategy

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.TOKEN
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.TokenResponse
import com.tradistonks.app.models.responses.UserResponse
import com.tradistonks.app.repository.StrategyRepository
import com.tradistonks.app.web.services.language.LanguageController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StrategyController(var langController: LanguageController){
    var strategies: List<Strategy>? = null
    val loading = mutableStateOf(false)

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
                langController.retrieveAllLanguagesOfUser(tokenResponse, navController)
            }
        })
    }

    fun runStrategyById(tokenResponse: TokenResponse, idStrategy: String) {
        loading.value = true
        StrategyRepository.runStrategyById(TOKEN, idStrategy, object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                loading.value = false
                Log.d(
                    "tradistonks-run",
                    "Code ${response.code()}, body = runStrategy, message = ${response.message()}}"
                )
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                loading.value = false
                Log.d("tradistonks-run", "Error : ${t.message}")
            }

        })
    }


}
