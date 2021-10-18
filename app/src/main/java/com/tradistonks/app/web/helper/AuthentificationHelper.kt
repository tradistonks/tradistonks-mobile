package com.tradistonks.app.web.helper

import android.util.Log
import com.tradistonks.app.ACCESS_TOKEN
import com.tradistonks.app.PREFERENCES
import com.tradistonks.app.models.responses.auth.TokenResponse

class AuthentificationHelper {
    companion object {
        fun retrieveTokenFromCookies(cookies: String): String{
            var token = cookies.substringAfter("yamete_senpai=")
            token = token.substringBefore("; Domain")
            Log.d("Token", token)
            return "yamete_senpai=$token"
        }

        fun retrieveTokenExpirationDateFromCookies(cookies: String): String {
            val expiration = cookies.substringAfter("Expires=")
            return expiration.substringBefore("; SameSite=")
        }

        fun retrieveTokenResponseFromCookies(cookies: String): TokenResponse {
            val token = cookies.let { retrieveTokenFromCookies(it) }
            val expirationDate = retrieveTokenExpirationDateFromCookies(cookies)
            val tokenResponse = TokenResponse(token, expirationDate)
            token.let { PREFERENCES?.setToken(it) }
            ACCESS_TOKEN = PREFERENCES?.getToken().toString()
            Log.d("Expiration date", tokenResponse.expiresIn)
            return tokenResponse
        }
    }
}