package com.tradistonks.app.repository

import android.net.Uri
import com.google.gson.JsonObject
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import com.tradistonks.app.web.services.auth.AuthentificationService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object AuthentificationRepository {
        private var loginChallenge: String = ""
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

        fun login(email: String, password:String, callback: Callback<Void>) {
            retrieveLoginChallenge()
            val login = Login(login_challenge = this.loginChallenge, email = email, password = password)
            val call = apiService?.login(login)
            call?.enqueue(callback)
        }

        fun retrieveLoginChallenge(){
            val thread = Thread {
                requestLoginChallenge()
            }
            thread.start()
            thread.join()
        }

        fun requestLoginChallenge(): String {
            val url = BuildConfig.OAUTH2_URL_CHALLENGE +
                    "client_id=${BuildConfig.OAUTH2_CLIENT_ID}"+
                    "&response_type=code&state=${BuildConfig.OAUTH2_PKCE_STATE}&scope=identify+offline"

            println("url login challenge:" + url)

            val client = OkHttpClient().newBuilder()
                .followSslRedirects(false)
                .build()
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                val responseUrl = response.request.url
                val uri: Uri = Uri.parse(responseUrl.toString())
                this.loginChallenge = uri.getQueryParameter("login_challenge").toString()
                return this.loginChallenge
            }
        }

    fun retrieveUser(token: String, callback: Callback<JsonObject>) {
        val call = apiService?.getCurrentUser(token)
        call?.enqueue(callback)
    }

}