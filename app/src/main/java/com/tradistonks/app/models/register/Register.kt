package com.tradistonks.app.models.register

data class Register(val email: String, val username: String,
                    val password: String, val password_confirmation: String)