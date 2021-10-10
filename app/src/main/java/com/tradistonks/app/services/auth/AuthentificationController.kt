package com.tradistonks.app.services.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.PREFERENCES
import com.tradistonks.app.models.UserRequest
import com.tradistonks.app.models.UserResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import com.tradistonks.app.repository.AuthentificationRepository
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
                    "Code ${response.code()}, body = Login, message = ${response.message()}, ${response.headers()}"
                )
                if(response.code() == 201){
                    var token: String? = response.headers()["Set-Cookie"]
                    token = token!!.substringAfter("yamete_senpai=")
                    token = token.substringBefore("; Domain=")

                    if(!token.isNullOrEmpty()){
                        token.let { PREFERENCES?.setToken(it) }
                        ACCESS_TOKEN = PREFERENCES?.getToken().toString()
                        Log.d(
                            "Token ${token}", "Access token ${ACCESS_TOKEN}"
                        )
                        //retrieveUser
                        navController.navigate("strategies")
                    }
                }else{
                    Toast.makeText(context,"Error in the email or the password", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun retrieveUser() {
        AuthentificationRepository.retrieveUser(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("tradistonks-user", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.d(
                    "tradistonks-user",
                    "Code ${response.code()}, body = Login, message = ${response.message()}, ${response.headers()}"
                )
            }
        })
    }
}
