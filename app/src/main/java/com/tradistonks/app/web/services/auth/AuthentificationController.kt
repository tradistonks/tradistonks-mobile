package com.tradistonks.app.web.services.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.tradistonks.app.models.TokenResponse
import com.tradistonks.app.models.UserResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import com.tradistonks.app.repository.AuthentificationRepository
import com.tradistonks.app.web.helper.AuthentificationHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthentificationController{

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

    fun login(email: String, password: String, context: Context, navController: NavHostController) {
        AuthentificationRepository.login(email,  password, object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("tradistonks-login", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.d(
                    "tradistonks-login",
                    "Code ${response.code()}, body = Login, message = ${response.message()}"
                )
                if(response.code() == 201){
                    val cookies: String? = response.headers()["Set-Cookie"]
                    val tokenResponse = cookies?.let {
                        AuthentificationHelper.retrieveTokenResponseFromCookies(it)
                    }
                    tokenResponse?.let { retrieveUser(it, navController) }
                }else{
                    Toast.makeText(context,"Error in the email or the password", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    fun retrieveUser(token: TokenResponse, navController: NavHostController) {
        AuthentificationRepository.retrieveUser(token, object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("tradistonks-user", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                Log.d(
                    "tradistonks-user",
                    "Code ${response.code()}, body = getUsers, message = ${response.message()}, ${response.headers()}"
                )
                navController.navigate("strategies")
            }
        })
    }
}
