package com.tradistonks.app.services.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.GLOBAL_USER
import com.tradistonks.app.PREFERENCES
import com.tradistonks.app.components.User
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AuthentificationController {

    fun register(data : Register) {
        AuthentificationRepository.register(data, object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("tradistonks-register", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.d(
                    "tradistonks-register",
                    "Code ${response.code()}, body = Register, message = ${response.message()}"
                )
                val body: RegisterResponse? = response.body()
                println(response)
            }
        })
    }

    fun login(email:String, password:String) {
        AuthentificationRepository.login(email,  password, object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("tradistonks-login", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d(
                    "tradistonks-login",
                    "Code ${response.code()}, body = Login, message = ${response.message()}"
                )
                val body: LoginResponse? = response.body()
                println(response)
                PREFERENCES?.setToken("Test")
                ACCESS_TOKEN = PREFERENCES?.getToken().toString()
                GLOBAL_USER = User("Test", email, Date())
            }
        })
    }
}
