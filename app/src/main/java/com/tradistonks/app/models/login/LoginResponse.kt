package com.tradistonks.app.models.login

import java.io.Serializable

data class LoginResponse(val token: String?, val message: String?) : Serializable