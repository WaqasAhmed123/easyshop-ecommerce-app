package com.example.easyshop.service

import android.content.Context
import androidx.credentials.CredentialManager

//import android.credentials.CredentialManager

object CredentialManagerService {
    var credentialManager : CredentialManager?=null


    fun initialize(context: Context) {
        if (credentialManager == null) {
            credentialManager = CredentialManager.create(context)
        }
    }

}