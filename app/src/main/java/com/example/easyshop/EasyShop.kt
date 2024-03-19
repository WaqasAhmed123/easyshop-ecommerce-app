package com.example.easyshop

import SharedPreferenceService
import android.app.Application

class EasyShop : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceService.initialize(this)
        println(" has token${SharedPreferenceService.hasToken()}")
    }
}
