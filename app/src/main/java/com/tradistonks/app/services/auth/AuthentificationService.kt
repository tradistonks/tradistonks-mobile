package com.tradistonks.app.services.auth

import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse

interface AuthentificationService {

    @POST("users")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun register(@Body body: Register): Call<RegisterResponse>

    @POST("/auth/login")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body body: Login): Call<LoginResponse>
}