package com.example.easyshop.view.profile

import SharedPreferenceService
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

object ProfileViewModel {
    var isSigningOut = mutableStateOf(false)


    fun signOut(navController: NavController) {
        isSigningOut.value = true
        navController.navigate("login_view") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            SharedPreferenceService.clearSharedPreferences()
        }
        isSigningOut.value = false
    }

}