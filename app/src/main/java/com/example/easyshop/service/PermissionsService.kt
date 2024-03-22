package com.example.easyshop.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.easyshop.repository.UserRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object PermissionsService {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun RequestNotificationPermissionDialog() {
        val permissionState =
            rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

        if (!permissionState.status.isGranted) {
//            if (permissionState.status.shouldShowRationale) RationaleDialog()
//            else {
            LaunchedEffect(key1 = Unit) {
                permissionState.launchPermissionRequest()
            }
//            }

        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
   fun requestLocationPermissionDialog(): MutableState<Boolean> {
        val permissionState =
            rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (!permissionState.status.isGranted) {
//            if (permissionState.status.shouldShowRationale) RationaleDialog()
//            else {
            LaunchedEffect(key1 = Unit) {
                permissionState.launchPermissionRequest()
//                fetchCurrentLocation(context = )
            }
//            }

        }
        return remember { mutableStateOf(permissionState.status.isGranted) }
    }

//    @Composable
//    fun fetchCurrentLocation(context: Context) {
////        val isPermissionGranted = remember { mutableStateOf(requestLocationPermissionDialog()) }
//        val isPermissionGranted = requestLocationPermissionDialog()
//
//        // Check if permission is granted
//        if (isPermissionGranted.value) {
//            try {
//                val fusedLocationClient: FusedLocationProviderClient =
//                    LocationServices.getFusedLocationProviderClient(context)
//
//                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                    // Fetch and process location data
//                    println("Current location: ${location.latitude}, ${location.longitude}")
//                    UserRepository.lat=location.latitude
//                    UserRepository.lon =location.longitude
//                }.addOnFailureListener { e ->
//                    // Handle failure
//                    println("Failed to fetch location: $e")
//                }
//            } catch (e: SecurityException) {
//                // Handle security exception
//                println("Security exception: $e")
//            }
//        } else {
//            // Request location permission
//            requestLocationPermissionDialog()
////            RequestLocationPermissionDialog()
//        }
//    }
//    @Composable
//    fun fetchCurrentLocation(context: Context) {
//        val isPermissionGranted = requestLocationPermissionDialog()
//
//        // Observe changes to the permission state
//        DisposableEffect(isPermissionGranted) {
//            if (isPermissionGranted.value) {
//                try {
//                    val fusedLocationClient: FusedLocationProviderClient =
//                        LocationServices.getFusedLocationProviderClient(context)
//
//                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                        // Fetch and process location data
//                        println("Current location: ${location.latitude}, ${location.longitude}")
//                        UserRepository.lat = location.latitude
//                        UserRepository.lon = location.longitude
//                    }.addOnFailureListener { e ->
//                        // Handle failure
//                        println("Failed to fetch location: $e")
//                    }
//                } catch (e: SecurityException) {
//                    // Handle security exception
//                    println("Security exception: $e")
//                }
//            }
//
//            // Don't forget to dispose the effect
//            onDispose { }
//        }
//    }
//@Composable
 fun fetchCurrentLocation(context: Context) {
//    val isPermissionGranted = requestLocationPermissionDialog()

    // Observe changes to the permission state
//    DisposableEffect(isPermissionGranted) {
//        if (isPermissionGranted.value) {
            try {
                val fusedLocationClient: FusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(context)

                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    // Fetch and process location data
                    UserRepository.lat = location.latitude
                    UserRepository.lon = location.longitude
                    println("Current location: ${UserRepository.lat}, ${UserRepository.lon}")
                }.addOnFailureListener { e ->
                    // Handle failure
                    println("Failed to fetch location: $e")
                }
            } catch (e: SecurityException) {
                // Handle security exception
                println("Security exception: $e")
            }
//        }

        // Don't forget to dispose the effect
    }
//}

}