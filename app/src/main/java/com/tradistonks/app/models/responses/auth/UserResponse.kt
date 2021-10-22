package com.tradistonks.app.models.responses.auth

import java.util.*

data class UserResponse(var _id: String, var username: String, var email: String, val created_date: Date?, val roles: List<String>?, var token: String?, var isStayingConnected : Boolean = true)