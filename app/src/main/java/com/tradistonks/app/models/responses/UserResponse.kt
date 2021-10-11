package com.tradistonks.app.models.responses

import java.util.*

data class UserResponse(var _id: String, var username: String, var email: String, val created_date: Date){
}