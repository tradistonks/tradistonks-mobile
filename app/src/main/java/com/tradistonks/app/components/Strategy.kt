package com.tradistonks.app.components

import java.util.*

data class Strategy(
    val _id: String, val user: String, val name: String,
    val language: String, val from: Date, val to: Date? = null, val updated_date: Date, val created_date: Date){
}

