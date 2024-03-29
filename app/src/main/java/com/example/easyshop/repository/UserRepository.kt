package com.example.easyshop.repository

import PreferenceDataStoreService
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.view.cart.CartItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

object UserRepository {
    //    val scope= rememberCoroutineScope()
    val userName = MutableStateFlow("")
    val cartProducts = MutableStateFlow(mutableListOf<CartItem>())
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