package com.example.easyshop

import DataStoreService
import android.app.Application
import com.example.easyshop.service.CredentialManagerService
import com.google.firebase.FirebaseApp
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.installations.FirebaseInstallations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EasyShop : Application() {
    override fun onCreate() {
        super.onCreate()
//        DataStoreService.initialize(this)
        CredentialManagerService.initialize(this)
        CoroutineScope(Dispatchers.Main).launch {

            // Initialize Firebase asynchronously
            FirebaseApp.initializeApp(this@EasyShop)
            FirebaseInAppMessaging.getInstance().isAutomaticDataCollectionEnabled = true

            println("id is ")
            println("got id ${FirebaseInstallations.getInstance().getId()}")

            // Now Firebase initialization is complete
            println("Firebase initialization complete")

            // Check if token is available after Firebase initialization
//            println("Has token: ${DataStoreService.hasToken(context = this@EasyShop)}")

            // Example: You can proceed with other initialization tasks here
            // ...
//            println(" has token${DataStoreService.hasToken(context = this@EasyShop)}")
        }


    }
}
