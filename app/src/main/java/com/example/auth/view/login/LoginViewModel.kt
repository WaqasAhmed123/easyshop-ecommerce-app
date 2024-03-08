package com.example.auth.view.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object LoginViewModel {
    var isLoggingIn = mutableStateOf(false)
    var email = mutableStateOf("")
    var password = mutableStateOf("")

}