package com.example.easyshop.model

import com.example.easyshop.service.FirebaseService

data class UserModel(
//    val userName:String,
    val userName:String="Waqas",
//    val email:String=FirebaseService.auth.currentUser?.email!!,
        val email: String = FirebaseService.auth.currentUser?.email ?: ""

)
