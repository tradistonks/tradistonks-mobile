package com.tradistonks.app.web.services.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.BuildConfig.TOKEN
import com.tradistonks.app.models.Register
import com.tradistonks.app.models.requests.UserUpdateRequest
import com.tradistonks.app.models.responses.RegisterResponse
import com.tradistonks.app.models.responses.auth.TokenResponse
import com.tradistonks.app.models.responses.auth.UserResponse
import com.tradistonks.app.repository.AuthentificationRepository
import com.tradistonks.app.web.services.strategy.StrategyController
import com.tradistonks.app.web.helper.AuthentificationHelper
import com.tradistonks.app.web.repository.room.AppDatabase
import com.tradistonks.app.web.repository.room.RoomUserRepository
import com.tradistonks.app.web.repository.room.UserDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthentificationController(var stratController: StrategyController, var userDao: UserDatabaseDao){
    val loading = mutableStateOf(false)
    var user: UserResponse? = null

    fun register(data : Register) {
        loading.value = true
        AuthentificationRepository.register(data, object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                loading.value = false
                Log.d("tradistonks-register", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                loading.value = false
                Log.d(
                    "tradistonks-register",
                    "Code ${response.code()}, body = Register, message = ${response.message()}"
                )
                val body: RegisterResponse? = response.body()
                println(response)
            }
        })
    }

    suspend fun login(email: String, password: String, navController: NavHostController){
        loading.value = true
        AuthentificationRepository.login(email,  password, object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                loading.value = false
                Log.d("tradistonks-login", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                loading.value = false
                Log.d(
                    "tradistonks-login",
                    "Code ${response.code()}, body = Login, message = ${response.message()}"
                )
                if(response.code() == 201){
                    val cookies: String? = response.headers()["Set-Cookie"]
                    val tokenResponse = cookies?.let {
                        AuthentificationHelper.retrieveTokenResponseFromCookies(it)
                    }
                    GlobalScope.launch(Unconfined) {
                        tokenResponse?.let { retrieveUser(it, navController) }
                        stratController.retrieveAllStrategiesOfCurrentUser(TokenResponse("", ""), navController)
                    }
                }
            }
        })
    }


    suspend fun retrieveUser(tokenResponse: TokenResponse, navController: NavHostController) {
        loading.value = true
        AuthentificationRepository.retrieveUser(TOKEN, object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                loading.value = false
                Log.d("tradistonks-user", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                loading.value = false
                val json = response.body()
                Log.d(
                    "tradistonks-user",
                    "Code ${response.code()}, body = getUsers, message = ${json}}"
                )
                user = Gson().fromJson(json, UserResponse::class.java)
                user!!.token = TOKEN
                //userDao.insert(user!!)
                //println(userDao.getAll())
            }
        })
    }

    suspend fun updateUser(idUser: String, newUserInfo: UserUpdateRequest, navController: NavHostController){
        loading.value = true
        AuthentificationRepository.updateUser(TokenResponse("", ""), idUser, newUserInfo, object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                loading.value = false
                Log.d("tradistonks-update", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                loading.value = false
                Log.d(
                    "tradistonks-update",
                    "Code ${response.code()}, body = updateUser, message = ${response.message()}, token = ${TOKEN}"
                )
                if(response.code() == 200){
                    val json = response.body()
                    user = Gson().fromJson(json, UserResponse::class.java)
                    navController.navigate("account")
                }/*else if(response.code() == 400){
                    Toast.makeText(context,"Please verify the email format", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Unknown error", Toast.LENGTH_SHORT).show()
                }*/
            }
        })
    }
}
