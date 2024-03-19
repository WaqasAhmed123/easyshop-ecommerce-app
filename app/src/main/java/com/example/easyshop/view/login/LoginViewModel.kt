package com.example.easyshop.view.login

import androidx.compose.runtime.mutableStateOf

object LoginViewModel {
    var isLoggingIn = mutableStateOf(false)
    var email = mutableStateOf("")
    var userName = mutableStateOf("")
    var password = mutableStateOf("")

}