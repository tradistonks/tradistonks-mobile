package com.tradistonks.app.controllers

import android.content.Context
import android.content.Intent
import android.util.Log
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import com.tradistonks.app.services.auth.AuthentificationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthentificationController {

    private fun register(data : Register) {
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
                    "Code ${response.code()}, body = Token(XXXXXX), message = ${response.message()}"
                )
                val body: RegisterResponse? = response.body()
                /*if (response.code() != 409) {
                    if (body != null) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        this@RegisterActivity.startActivity(intent)
                    } else {
                        errorMessage.text = this@RegisterActivity.getText(R.string.errorRegisterMessage);
                    }
                } else {
                    errorMessage.text = this@RegisterActivity.getText(R.string.errorRegisterMessage);
                }*/
            }
        })
    }

    fun login(data: Login) {
        AuthentificationRepository.login(data, object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("tradistonks-login", "Error : ${t.message}")
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d(
                    "tradistonks-login",
                    "Code ${response.code()}, body = Token(XXXX), message = ${response.message()}"
                )
                val body: LoginResponse? = response.body()
                /*if (response.code() != 401) {
                    if (body != null) {
                        val sharedPref = this@LoginActivity.getPreferences(Context.MODE_PRIVATE) ?: return
                        with(sharedPref.edit()) {
                            putString(getString(R.string.token), body.token)
                            apply()
                            val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                            this@LoginActivity.startActivity(intent)
                        }
                    } else {
                        errorMessage.text = this@LoginActivity.getText(R.string.errorLoginMessage);
                    }
                } else {
                    errorMessage.text = this@LoginActivity.getText(R.string.errorLoginMessage);
                }*/
            }
        })
    }
}
