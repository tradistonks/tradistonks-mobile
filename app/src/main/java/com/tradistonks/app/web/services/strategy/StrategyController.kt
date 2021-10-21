package com.tradistonks.app.web.services.strategy

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tradistonks.app.BuildConfig.TOKEN
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.auth.TokenResponse
import com.tradistonks.app.models.responses.strategy.RunResultDto
import com.tradistonks.app.models.responses.strategy.StrategyResponse
import com.tradistonks.app.models.responses.strategy.StrategySerializable
import com.tradistonks.app.repository.StrategyRepository
import com.tradistonks.app.web.services.language.LanguageController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.stream.Collectors

class StrategyController(var langController: LanguageController, var applicationContext: Context){
    var strategies: ArrayList<Strategy>? = null
    val loading = mutableStateOf(false)
    val gson = GsonBuilder().setPrettyPrinting().create()

    fun getStrategyById(strategyId: String): Strategy{
        return strategies!!.first { s -> s._id == strategyId }
    }

    suspend fun retrieveAllStrategiesOfCurrentUser(tokenResponse: TokenResponse, navController: NavHostController) {
        StrategyRepository.retrieveAllStrategiesOfCurrentUser(TOKEN, object : Callback<List<StrategyResponse>>{
            override fun onFailure(call: Call<List<StrategyResponse>>, t: Throwable) {
                Log.d("tradistonks-strategies", "Error : ${t.message}")
                Toast.makeText(applicationContext, "Error during the retrieve of the strategies. Verify your connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<StrategyResponse>>,
                response: Response<List<StrategyResponse>>
            ) {
                val responseStrategies = response.body()
                strategies = responseStrategies!!.stream().map(StrategyResponse::toStrategy).collect(
                    Collectors.toList()) as ArrayList<Strategy>

                Log.d(
                    "tradistonks-strategies",
                    "Code ${response.code()}, body = getStrategies, message = ${response.message()}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    async {
                        getAllStrategiesFromLocalBdd()
                    }.await()
                    langController.retrieveAllLanguagesOfUser(tokenResponse, navController)
                }
            }
        })
    }

    suspend fun updateStrategiesInLocalBdd(){
        val filename = "strategies"
        val strategiesSerializable = strategies?.stream()?.map(Strategy::toStrategySerializable)?.collect(Collectors.toList())
        val fileContents = gson.toJson(strategiesSerializable)
        applicationContext.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
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
                        "Code ${response.code()}, body = runStrategy}"
                    )
                    val json =  response.body()
                    var results: RunResultDto? = null
                    try {
                        results = gson.fromJson(json, RunResultDto::class.java)
                    }
                    catch (e: Exception){
                        Log.d( "Exception",
                            e.toString()
                        )
                    }
                    if(results != null){
                        strategy.hasResults.value = true
                        strategy.results = results
                        strategy.last_run = Date()
                        GlobalScope.launch(Dispatchers.IO) {
                            updateStrategiesInLocalBdd()
                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    strategy.loading.value = false
                    Log.d("tradistonks-run", "Error : ${t.message}")
                    Toast.makeText(applicationContext, "Error during the launch of the strategy. " +
                            "Please verify the code of your strategy", Toast.LENGTH_LONG).show()
                }
            })
        }

    }

    fun getAllStrategiesFromLocalBdd(){
        applicationContext.openFileInput("strategies").use { stream ->
            val text = stream.bufferedReader().use {
                it.readText()
            }
            val type = object : TypeToken<ArrayList<StrategySerializable>>() {}.type
            val strategiesSerializable = gson.fromJson<ArrayList<StrategySerializable>>(text, type)
            val strategiesDeserializable = strategiesSerializable.stream().map(StrategySerializable::toStrategy).collect(Collectors.toList())
            if(strategies.isNullOrEmpty() and !strategiesDeserializable.isNullOrEmpty()) {
                strategies = strategiesDeserializable as ArrayList<Strategy>?
            }else if(strategies.isNullOrEmpty() and strategiesDeserializable.isNullOrEmpty()){
                Toast.makeText(applicationContext, "You do not own any strategies. Please create one on the website", Toast.LENGTH_LONG).show()
            }else{
                for(strategy in strategiesDeserializable!!){
                    strategies!!.stream().filter{ strat -> strat._id == strategy._id}.forEach {
                            strat-> strat.results = strategy.results
                            strat.hasResults = strategy.hasResults
                            strat.last_run = strategy.last_run
                    }
                }
            }

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
