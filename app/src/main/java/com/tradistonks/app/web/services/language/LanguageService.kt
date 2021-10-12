package com.tradistonks.app.web.services.language

import com.tradistonks.app.models.Language
import retrofit2.Call
import retrofit2.http.*

interface LanguageService {

    @GET("/languages")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun retrieveAllLanguagesOfUser(@Header("Cookie") token: String): Call<List<Language>>

}