package com.example.easyshop.model

data class LoginResponse(
    val token: String
)

data class LoginRequest(
    val username: String,
    val password: String
)
