package com.example.easyshop.view.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.SubmitButton
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.service.PermissionsService
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import textStyle

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileView(navController: NavController, profileViewModel: ProfileViewModel) {
    val username by profileViewModel.userName.collectAsState()


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackBarState = remember { SnackbarHostState() }
    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    ) { granted ->
        if (granted) {
            navController.navigate("map_view")
        } else {
            Toast.makeText(
                context, "Location permission denied", Toast.LENGTH_SHORT
            ).show()


        }

    }
    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(

                painter = painterResource(id = R.drawable.signup),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    //                        .size(size)
                    .clip(CircleShape)// Clip the image to a circular shape
            )

            Text(
//                text = "Welcome ${UserRepository.userName.value}",
                text = "Welcome $username",
//                text = "Welcome ${UserModel().email}",
//                style = textStyle()["titleLarge"]!!,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W500),
//                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.run { height(20.dp) })
            SubmitButton(
                onClick = {
                    scope.launch(Dispatchers.Main) {
//                        val permissionResult = async {
//                        locationPermissionState.launchPermissionRequest()
//                        }.await()
                        if (locationPermissionState.status.isGranted) {
                            navController.navigate("map_view")

                        } else {
                            locationPermissionState.launchPermissionRequest()
                        }

//                        if (!locationPermissionState.status.isGranted) {
//                            // Permission denied
//                            Toast.makeText(
//                                context, "Location permission denied", Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
                        // Permission granted, navigate to map view
//                            navController.navigate("map_view")
//                        }

//                        async { PermissionsService.fetchCurrentLocation(context = context) }.await()
//                         PermissionsService.fetchCurrentLocation(context = context)

                    }
//                    scope.launch(Dispatchers.Main) {
//                        job.join()
//                        println("checking latlng before navigation ${UserRepository.lat} ${UserRepository.lon}")
//
////                        ProfileViewModel.signOut(navController)
////                        FirebaseService.signOut(navController = navController)
//
//
//                    }

                }, buttonTitle = "Track Your Orders"
            )
            Spacer(modifier = Modifier.height(10.dp))
            SubmitButton(
                onClick = {
                    scope.launch {
                        profileViewModel.signOut(navController, context = context)
//                        FirebaseService.signOut(navController = navController)


                    }

                }, buttonTitle = "Logout", isLoading = profileViewModel.isSigningOut
            )
        }

    }

}