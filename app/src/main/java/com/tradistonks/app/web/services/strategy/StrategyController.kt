package com.tradistonks.app.web.services.strategy

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.android.material.circularreveal.CircularRevealHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig.TOKEN
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.auth.TokenResponse
import com.tradistonks.app.models.responses.strategy.RunResultDto
import com.tradistonks.app.models.responses.strategy.StrategyResponse
import com.tradistonks.app.repository.StrategyRepository
import com.tradistonks.app.web.repository.room.StrategyDatabaseDao
import com.tradistonks.app.web.services.language.LanguageController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class StrategyController(var langController: LanguageController, val strategyDao: StrategyDatabaseDao){
    var strategies: List<Strategy>? = null
    val loading = mutableStateOf(false)

    fun getStrategyById(strategyId: String): Strategy{
        return strategies!!.first { s -> s._id == strategyId }
    }

    suspend fun retrieveAllStrategiesOfCurrentUser(tokenResponse: TokenResponse, navController: NavHostController) {
        StrategyRepository.retrieveAllStrategiesOfCurrentUser(TOKEN, object : Callback<List<StrategyResponse>>{
            override fun onFailure(call: Call<List<StrategyResponse>>, t: Throwable) {
                Log.d("tradistonks-strategies", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<StrategyResponse>>,
                response: Response<List<StrategyResponse>>
            ) {
                var responseStrategies = response.body()
                strategies = responseStrategies!!.stream().map(StrategyResponse::toStrategy).collect(
                    Collectors.toList())

                Log.d(
                    "tradistonks-strategies",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}, json = $strategies"
                )
                GlobalScope.launch(Dispatchers.Unconfined) {
                    updateStrategiesInLocalBdd()
                    langController.retrieveAllLanguagesOfUser(tokenResponse, navController)
                }
            }
        })
    }

    suspend fun updateStrategiesInLocalBdd(){
        /*for(strategy in strategies!!){
            if(strategyDao.getById(strategy._id) != null){
                strategyDao.insert(strategy)
            }else{
                strategyDao.update(strategy)
            }
        }
        println(strategyDao.getById(strategies!![0]._id))*/
    }

    fun runStrategyById(tokenResponse: TokenResponse, strategy: Strategy) {
        strategy.loading.value = true
        GlobalScope.launch(Dispatchers.Unconfined) {
            StrategyRepository.runStrategyById(TOKEN, strategy._id, object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    strategy.loading.value = false
                    Log.d(
                        "tradistonks-run",
                        "Code ${response.code()}, body = runStrategy, message = ${response.message()}}"
                    )
                    val json =  response.body()
                    var results: RunResultDto? = null
                    try {
                        results = Gson().fromJson(json, RunResultDto::class.java)
                        //println(results.toString())
                    }
                    catch (e: Exception){
                        Log.d( "Exception",
                            e.toString()
                        )
                    }
                    if(results != null){
                        strategy.hasResults.value = true
                        strategy.results = results
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    strategy.loading.value = false
                    Log.d("tradistonks-run", "Error : ${t.message}")
                }
            })
        }

    }


}
