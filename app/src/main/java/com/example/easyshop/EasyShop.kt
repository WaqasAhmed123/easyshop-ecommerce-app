package com.example.easyshop

import SharedPreferenceService
import android.app.Application
import android.credentials.CredentialManager
import com.example.easyshop.service.CredentialManagerService

class EasyShop : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceService.initialize(this)
        CredentialManagerService.initialize(this)
        println(" has token${SharedPreferenceService.hasToken()}")
    }
}
