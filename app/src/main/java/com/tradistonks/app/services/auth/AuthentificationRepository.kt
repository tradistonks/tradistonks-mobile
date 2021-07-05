package com.tradistonks.app.services.auth

import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import org.jetbrains.hub.oauth2.client.AccessTokenSource
import org.jetbrains.hub.oauth2.client.jersey.oauth2Client
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URI

object AuthentificationRepository {
    private var apiService: AuthentificationService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(
                BuildConfig.TRADISTONKSAPIBASEURL
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(AuthentificationService::class.java)
    }

    fun register(data: Register, callback: Callback<RegisterResponse>) {
        val call = apiService?.register(data)
        call?.enqueue(callback)
    }

    fun login(email: String, password: String) {
        val clientID = System.getenv("oauth2_client_id")
        val accessTokenSource: AccessTokenSource = oauth2Client().resourceOwnerFlow(
            tokenEndpoint = URI(System.getenv("oauth2_url_token")),
            username = email,
            password = password,
            clientID = clientID,
            clientSecret = System.getenv("oauth2_client_secret"),
            scope = listOf("0-0-0-0-0", clientID)
        )
        ACCESS_TOKEN = accessTokenSource.accessToken
    }



}