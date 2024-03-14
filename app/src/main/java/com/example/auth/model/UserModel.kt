package com.example.auth.model

import com.example.auth.service.FirebaseService

data class UserModel(
//    val userName:String,
    val userName:String="Waqas",
    val email:String=FirebaseService.auth.currentUser?.email!!,
)
