package com.tradistonks.app.web.services.auth

import com.tradistonks.app.models.UserResponse
import com.tradistonks.app.models.login.Login
import retrofit2.Call
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import retrofit2.http.*

interface AuthentificationService {

    @POST("users")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun register(@Body body: Register): Call<RegisterResponse>

    @POST("/auth/login")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun login(@Body body: Login): Call<Void>

    @GET("/users/me")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getCurrentUser(@Header("Cookie") token: String): Call<UserResponse>

}