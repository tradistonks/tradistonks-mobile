package com.tradistonks.app.web.services.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tradistonks.app.TOKEN
import com.tradistonks.app.models.responses.TokenResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import com.tradistonks.app.models.responses.UserResponse
import com.tradistonks.app.models.user.UserUpdateRequest
import com.tradistonks.app.repository.AuthentificationRepository
import com.tradistonks.app.web.services.strategy.StrategyController
import com.tradistonks.app.web.helper.AuthentificationHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthentificationController(var stratController: StrategyController){
    var token: TokenResponse? = null
    var user: UserResponse? = null

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

    fun login(email: String, password: String, context: Context, navController: NavHostController){
        AuthentificationRepository.login(email,  password, object : Callback<Void>{
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
                    stratController.retrieveAllStrategiesOfCurrentUser(TokenResponse("", ""), navController)
                }else{
                    Toast.makeText(context,"Error in the email or the password", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    fun retrieveUser(tokenResponse: TokenResponse, navController: NavHostController) {
        AuthentificationRepository.retrieveUser(TOKEN, object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("tradistonks-user", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                val json = response.body()
                Log.d(
                    "tradistonks-user",
                    "Code ${response.code()}, body = getUsers, message = ${json}}"
                )
                user = Gson().fromJson(json, UserResponse::class.java)
            }
        })
    }

    fun updateUser(idUser: String, newUserInfo: UserUpdateRequest, context: Context, navController: NavHostController){
        Log.d(
            "tradistonks-update",
            "id ${idUser}, newUser = ${newUserInfo}"
        )
        AuthentificationRepository.updateUser(TOKEN, idUser, newUserInfo, object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("tradistonks-update", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                Log.d(
                    "tradistonks-update",
                    "Code ${response.code()}, body = updateUser, message = ${response.message()}"
                )
                if(response.code() == 200){
                    val json = response.body()
                    user = Gson().fromJson(json, UserResponse::class.java)
                    navController.navigate("account")
                }else{
                    Toast.makeText(context,"Error during the update of the user", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
