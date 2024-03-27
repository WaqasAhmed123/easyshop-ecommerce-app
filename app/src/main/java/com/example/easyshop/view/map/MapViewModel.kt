package com.example.easyshop.view.map

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.service.PermissionsService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.StateFlow

class MapViewModel : ViewModel() {
    //    var currentLocation = mutableStateOf(LatLng(0.0,0.0))
    var currentLocation: StateFlow<LatLng> = UserRepository.userLocation
    suspend fun getCurrentLocation(context: Context) {

        PermissionsService.fetchCurrentLocation(context = context)
    }
//private val _currentLocation = MutableStateFlow(LatLng(0.0, 0.0))
//var currentLocation = MutableStateFlow(UserRepository.userLocation.value )

//    val currentLocation: StateFlow<LatLng> = UserRepository.userLocation


//    suspend fun getCurrentLocation(context: Context) {
//        PermissionsService.fetchCurrentLocation(context = context) { lat, lon ->
////            _currentLocation.value = LatLng(lat, lon)
//            currentLocation.value = LatLng(lat, lon)
//        }
//    }

//    init {
//        viewModelScope.launch {
//            getCurrentLocation()
//        }

//    }
}