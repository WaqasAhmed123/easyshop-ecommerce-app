package com.example.easyshop.repository

import SharedPreferenceService
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.easyshop.service.FirebaseService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object UserRepository {
    //    val scope= rememberCoroutineScope()
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    fun loadUsername(context: Context, coroutineScope: CoroutineScope) {
        val sharedPreferenceService = SharedPreferenceService(context)

        coroutineScope.launch {
            sharedPreferenceService.getUsername(context).collect { username ->
                _userName.value = username ?: ""
                println("name is ")
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