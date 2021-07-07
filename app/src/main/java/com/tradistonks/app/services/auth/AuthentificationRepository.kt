package com.tradistonks.app.services.auth

import android.net.Uri
import com.tradistonks.app.BuildConfig
import com.tradistonks.app.models.login.Login
import com.tradistonks.app.models.login.LoginResponse
import com.tradistonks.app.models.register.Register
import com.tradistonks.app.models.register.RegisterResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object AuthentificationRepository {
    private var loginChallenge: String? = ""
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

    fun login(email: String, password:String, callback: Callback<LoginResponse>) {
        retrieveLoginChallenge()
        val logUser = this.loginChallenge?.let { Login(it, email, password) }
        val call = logUser?.let { apiService?.login(it) }
        call?.enqueue(callback)
    }

    fun retrieveLoginChallenge(){
        val thread = Thread {
            requestLoginChallenge()
        }
        thread.start()
        thread.join()
    }

    fun requestLoginChallenge(): String? {

        val url = BuildConfig.OAUTH2_URL_CHALLENGE +
                "client_id=${BuildConfig.OAUTH2_CLIENT_ID}&" +
                "redirect_uri=${BuildConfig.OAUTH2_REDIRECT_URL_CHALLENGE}" +
                "&response_type=code&state=${BuildConfig.OAUTH2_PKCE_STATE}&scope=identify+offline"

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
            val url = response.request.url
            val uri: Uri = Uri.parse(url.toString())
            this.loginChallenge = uri.getQueryParameter("login_challenge")
            return this.loginChallenge
        }
    }


}