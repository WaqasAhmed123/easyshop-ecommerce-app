package com.example.easyshop.repository

import SharedPreferenceService
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.easyshop.service.FirebaseService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object UserRepository {
    val userName: String = SharedPreferenceService?.getUsername() ?: ""

    //    val email:String=FirebaseService.auth.currentUser?.email!!,
    val email: String = FirebaseService.auth.currentUser?.email ?: ""

    //    var lat by mutableDoubleStateOf<Double>(0.0)
//    var lon by mutableStateOf<Double>(0.0)
    var lat by mutableDoubleStateOf(0.0)
    var lon by mutableDoubleStateOf(0.0)

}