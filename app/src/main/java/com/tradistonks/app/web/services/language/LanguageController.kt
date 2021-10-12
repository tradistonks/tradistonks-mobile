package com.tradistonks.app.web.services.language

import android.util.Log
import androidx.navigation.NavHostController
import com.google.gson.JsonObject
import com.tradistonks.app.TOKEN
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.Language
import com.tradistonks.app.models.responses.TokenResponse
import com.tradistonks.app.repository.LanguageRepository
import com.tradistonks.app.repository.StrategyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguageController{
    var languages: List<Language>? = null

    fun getNameOfLanguage(id: String): String? {
        return languages?.filter { it._id.equals(id) }?.map { it.name }?.get(0)
    }

    fun retrieveAllLanguagesOfUser(tokenResponse: TokenResponse, navController: NavHostController) {
        LanguageRepository.retrieveAllLanguagesOfUser(TOKEN, object : Callback<List<Language>>{
            override fun onFailure(call: Call<List<Language>>, t: Throwable) {
                Log.d("tradistonks-languages", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Language>>,
                response: Response<List<Language>>
            ) {
                languages = response.body()
                Log.d(
                    "tradistonks-languages",
                    "Code ${response.code()}, body = getLanguages, message = ${response.message()}, json = $languages"
                )
                navController.navigate("strategies")
            }
        })
    }


}