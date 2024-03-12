package com.example.auth.view.home

import androidx.compose.runtime.mutableStateOf

object HomeViewModel {
    var isSigningOut= mutableStateOf(false)
    var userName= mutableStateOf("Waqas")
    var search= mutableStateOf("")
    val itemsPrice: MutableList<String> = mutableListOf("$40", "\$40", "\$40")
    val itemsName: MutableList<String> = mutableListOf("Watch","Nike","Bat")

}