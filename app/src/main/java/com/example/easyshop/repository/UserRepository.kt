package com.example.easyshop.repository

import DataStoreService
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.easyshop.service.FirebaseService
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


    fun loadUsername(context: Context, coroutineScope: CoroutineScope) {
        val dataStoreService = DataStoreService(context)

        coroutineScope.launch {
            dataStoreService.getUsername(context).collect { username ->
                userName.value = username ?: ""
                println("name is ${userName.value} private")
                println("name is ${username} public")
                dataStoreService.getToken(context = context).collect { token ->
                    println("token is $token")
                }
            }
        }
    }

//    val userName: String = ""

    //    val email:String=FirebaseService.auth.currentUser?.email!!,
    val email: String = FirebaseService.auth.currentUser?.email ?: ""
    var userLocation = MutableStateFlow(LatLng(0.0, 0.0))
//    var userLocation: StateFlow<LatLng> = _userLocation

    //    var lat by mutableDoubleStateOf<Double>(0.0)
//    var lon by mutableStateOf<Double>(0.0)
//    var lat by mutableDoubleStateOf(0.0)
//    var lon by mutableDoubleStateOf(0.0)
//    var lat = mutableStateOf(0.0)
//    var lon = mutableStateOf(0.0)


}