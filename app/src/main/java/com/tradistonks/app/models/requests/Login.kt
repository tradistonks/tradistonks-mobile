package com.tradistonks.app.models.requests

data class Login(val login_challenge : String, val email: String, val password: String)