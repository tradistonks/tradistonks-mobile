package com.tradistonks.app.models.requests

data class UserUpdateRequest(var username: String, var email: String, val roles: List<String>?){
}