package com.example.easyshop.view.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.easyshop.model.LoginRequest
import com.example.easyshop.repository.ProductsRepository

class LoginViewModel : ViewModel() {
    var isLoggingIn = mutableStateOf(false)
    var email = mutableStateOf("")
    var userName = mutableStateOf("")
    var password = mutableStateOf("")

    suspend fun login(context: Context, navController: NavController) {
        isLoggingIn.value = true
        val loginResult = ProductsRepository.loginFromApi(
            loginCredentials = LoginRequest(
                username = userName.value, password = password.value
            ), context = context, navController = navController
        )
        isLoggingIn.value = false
        if (loginResult) {
            userName.value = ""
            password.value = ""
        }
    }

}