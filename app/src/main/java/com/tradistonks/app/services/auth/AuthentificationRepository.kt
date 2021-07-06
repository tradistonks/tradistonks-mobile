package com.tradistonks.app.services.auth

import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun login(data: Login, callback: Callback<LoginResponse>) {
        val call = apiService?.login(data)
        call?.enqueue(callback)
    }


}