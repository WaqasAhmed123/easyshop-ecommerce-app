package com.example.easyshop.repository

import PreferenceDataStoreService
import android.content.Context
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object UserRepository {
    //    val scope= rememberCoroutineScope()
    val userName = MutableStateFlow("")
    var userLocation = MutableStateFlow(LatLng(0.0, 0.0))
    
    


    fun loadUsername(context: Context, coroutineScope: CoroutineScope) {
        val preferenceDataStoreService = PreferenceDataStoreService(context)

        coroutineScope.launch {
            preferenceDataStoreService.getUsername(context).collect { username ->
                userName.value = username ?: ""
                println("name is ${userName.value} private")
                println("name is ${username} public")
                preferenceDataStoreService.getToken(context = context).collect { token ->
                    println("token is $token")
                }
            }
        }
    }

//    val email: String = FirebaseService.auth.currentUser?.email ?: ""

}