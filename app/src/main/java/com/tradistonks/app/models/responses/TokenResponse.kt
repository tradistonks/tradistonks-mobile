package com.tradistonks.app.models.responses

import java.util.*

data class TokenResponse(
    var token: String,
    var expiresIn: String
)