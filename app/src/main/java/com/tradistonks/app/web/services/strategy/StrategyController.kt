package com.tradistonks.app.web.services.strategy

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig.TOKEN
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.database.StrategyItem
import com.tradistonks.app.models.responses.auth.TokenResponse
import com.tradistonks.app.models.responses.strategy.RunResultDto
import com.tradistonks.app.models.responses.strategy.StrategyResponse
import com.tradistonks.app.repository.StrategyRepository
import com.tradistonks.app.web.repository.room.RoomStrategyRepository
import com.tradistonks.app.web.repository.room.StrategyDatabaseDao
import com.tradistonks.app.web.services.language.LanguageController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.stream.Collectors

class StrategyController(var langController: LanguageController, val strategyDao: StrategyDatabaseDao){
    var strategies: List<Strategy>? = null
    val loading = mutableStateOf(false)
    var localRepository : RoomStrategyRepository = RoomStrategyRepository(strategyDao)

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
                val responseStrategies = response.body()
                strategies = responseStrategies!!.stream().map(StrategyResponse::toStrategy).collect(
                    Collectors.toList())

                Log.d(
                    "tradistonks-strategies",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}, json = $strategies"
                )
                GlobalScope.launch(Dispatchers.IO) {
                    updateStrategiesInLocalBdd()
                    langController.retrieveAllLanguagesOfUser(tokenResponse, navController)
                }
            }
        })
    }

    suspend fun updateStrategiesInLocalBdd(){
        for(strategy in strategies!!){
            val strategyLocal = StrategyItem.toStrategy(localRepository.getStrategyById(strategy._id))
            println(strategyLocal)
            if(strategyLocal == null){
                localRepository.addStrategy(strategy)
            }else{
                strategy.hasResults = strategyLocal.hasResults
                strategy.last_run = strategyLocal.last_run
                localRepository.updateStrategy(strategy)
            }
            println(localRepository.getAllStrategies())
        }
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

    fun getAllOrdersFromStrategies(): List<Order> {
        val results: List<RunResultDto> = strategies!!.stream().filter(Objects::nonNull)
            .map(Strategy::results).filter(Objects::nonNull).collect(Collectors.toList()) as List<RunResultDto>
        val orders: ArrayList<Order> =
            results.stream().map(RunResultDto::orders).flatMap { it?.stream() }.collect(Collectors.toList()) as ArrayList<Order>
         orders.stream().map(Order::timestamp).sorted()
        return orders
    }


}
