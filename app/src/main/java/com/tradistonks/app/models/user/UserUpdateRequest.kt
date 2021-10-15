package com.tradistonks.app.models.user

data class UserUpdateRequest(var username: String, var email: String, val roles: List<String>?){
}