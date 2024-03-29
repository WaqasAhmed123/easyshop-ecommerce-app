package com.example.easyshop.view.profile

import DataStoreService
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.easyshop.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var isSigningOut = mutableStateOf(false)
    val userName: StateFlow<String> = UserRepository.userName



    fun signOut(navController: NavController,context:Context) {
        isSigningOut.value = true
        navController.navigate("login_view") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            viewModelScope.launch {
                val dataStoreService=DataStoreService(context)
                dataStoreService.clearDataStore(context = context)

            }
        }
        isSigningOut.value = false
    }
//    added
//fun Context.isPermissionGranted(name: String): Boolean {
//    return ContextCompat.checkSelfPermission(
//        this, name
//    ) == PackageManager.PERMISSION_GRANTED
//}
//
//    fun Activity.shouldShowRationale(name: String): Boolean {
//        return shouldShowRequestPermissionRationale(name)
//    }
//
//    fun Context.hasPickMediaPermission(): Boolean {
//
//        return when {
//            // If Android Version is Greater than Android Pie!
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> true
//
//            else -> isPermissionGranted(name = READ_EXTERNAL_STORAGE)
//        }
//    }
//
//    fun Context.gotoApplicationSettings() {
//        startActivity(Intent().apply {
//            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//            data = Uri.parse("package:${packageName}")
//        })
//    }
//
//    fun Context.findActivity(context: Context): Activity? {
//        return when (this) {
//            is Activity -> this
//            is ContextWrapper -> {
//                baseContext.findActivity(context)
//            }
//
//            else -> null
//        }
//    }
//
//    fun SnackbarHostState.showSnackBar(
//        message: String? = null,
//        action: String? = null,
//        duration: SnackbarDuration = Short,
//        coroutineScope: CoroutineScope,
//        onSnackBarAction: () -> Unit = {},
//        onSnackBarDismiss: () -> Unit = {},
//    ) {
//        if (!message.isNullOrEmpty()) {
//
//            coroutineScope.launch {
//
//                when (showSnackbar(
//                    message = message,
//                    duration = duration,
//                    actionLabel = action,
//                    withDismissAction = duration == Indefinite,
//                )) {
//                    SnackbarResult.Dismissed -> onSnackBarDismiss.invoke()
//                    SnackbarResult.ActionPerformed -> onSnackBarAction.invoke()
//                }
//            }
//        }
//    }
//

}