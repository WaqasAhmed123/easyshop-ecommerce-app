package com.example.easyshop

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.ui.theme.AuthTheme
import com.example.easyshop.view.app.AppView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.ktx.Firebase

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PREF_NAME")

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseService.auth = Firebase.auth
        FirebaseInAppMessaging.getInstance().isAutomaticDataCollectionEnabled = true

//        ProductsRepository.getAllCategoriesFromApi()

        setContent {
            AuthTheme {
                AppView()

            }
        }
    }
}


