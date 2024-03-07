package com.example.auth.view.signup

import androidx.compose.runtime.mutableStateOf

object SignupViewModel {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
}