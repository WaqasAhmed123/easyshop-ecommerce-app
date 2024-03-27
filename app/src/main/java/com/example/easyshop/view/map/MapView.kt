package com.example.easyshop.view.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.service.PermissionsService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.play.integrity.internal.f
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import kotlinx.coroutines.delay

@Composable
fun MapView(navController: NavController, mapViewModel: MapViewModel) {
    val currentLocation by mapViewModel.currentLocation.collectAsState()
//    val currentLocation by mapViewModel.currentLocation.collectAsState()
    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true, compassEnabled = true, mapToolbarEnabled = true
            )
        )
    }
//    uiSettings.myLocationButtonEnabled
//    uiSettings.compassEnabled
//    var properties by remember {
//        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
//    }
    val context = LocalContext.current
//    val initialLocation = remember {
//        mutableStateOf(LatLng(UserRepository.lat.value, UserRepository.lon.value))
//    }
//    val location = LatLng(UserRepository.lat.value, UserRepository.lon.value)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 10f)
    }




    GoogleMap(
        properties = MapProperties(isMyLocationEnabled = true),

//        properties = properties,
        uiSettings = uiSettings,


        modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = currentLocation),
//            title = "Singapore",
//            snippet = "Marker in Singapore"
        )

    }


//        MapUiSettings(myLocationButtonEnabled = true, compassEnabled = true)

//    LaunchedEffect(key1 = UserRepository.lat.value + UserRepository.lon.value) {
    LaunchedEffect(key1 = currentLocation.latitude + currentLocation.longitude) {
        println("launch called location before in map ${currentLocation}")
//        PermissionsService.fetchCurrentLocation(context = context)
//        delay(2000)
        mapViewModel.getCurrentLocation(context = context)
        println("launch called location after ${currentLocation}")

        // Animate camera to the new location
        cameraPositionState.animate(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(
//                    LatLng(UserRepository.lat.value, UserRepository.lon.value), 15f
                    currentLocation, 18f
                )
            )
        )
    }
}
//    Switch(checked = uiSettings.zoomControlsEnabled, onCheckedChange = {
//        uiSettings = uiSettings.copy(zoomControlsEnabled = it)
//    })

