package com.example.easyshop.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

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
}