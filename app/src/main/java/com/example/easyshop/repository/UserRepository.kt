package com.example.easyshop.repository

import SharedPreferenceService
import com.example.easyshop.service.FirebaseService

data class UserRepository(
    val userName:String=SharedPreferenceService?.getUsername()?:"",
//    val email:String=FirebaseService.auth.currentUser?.email!!,
    val email: String = FirebaseService.auth.currentUser?.email ?: ""
)
