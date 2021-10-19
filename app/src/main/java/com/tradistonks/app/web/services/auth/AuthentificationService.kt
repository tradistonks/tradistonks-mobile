package com.tradistonks.app.web.services.auth

import com.google.gson.JsonObject
import com.tradistonks.app.models.requests.Login
import retrofit2.Call
import com.tradistonks.app.models.Register
import com.tradistonks.app.models.responses.RegisterResponse
import com.tradistonks.app.models.responses.auth.AuthCallbackDto
import com.tradistonks.app.models.responses.auth.ConsentResponseDto
import com.tradistonks.app.models.requests.UserUpdateRequest
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
    fun getCurrentUser(@Header("Cookie") token: String): Call<JsonObject>

    @PUT("/users/{idUser}")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun updateUser(@Header("Cookie") token: String,
                   @Path("idUser") idUser: String,
                   @Body userUpdateRequest: UserUpdateRequest): Call<JsonObject>


    @POST("/auth/consent")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun consent(@Body consent_challenge: String): Call<ConsentResponseDto>

    @GET("/auth/consent")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getConsent(@Query("consent_challenge") consent_challenge: String): Call<String>

    @POST("/auth/callback/local")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun authLocalCallback(@Query("code") code: String, @Query("state") state: String): Call<AuthCallbackDto>


}