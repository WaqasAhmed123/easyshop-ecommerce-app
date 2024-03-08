package com.example.auth.service

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.auth.view.signup.SignupViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object FirebaseService {
    // Initialize Firebase Auth
//    lateinit var auth: FirebaseAuth
    val auth = Firebase.auth

    fun addUser(email: String, password: String, context: Context) {
        SignupViewModel.isCreating.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
                    println(
                        "user created"

                    )
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    val exception = task.exception
                    println("failed to create user $$exception")
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        SignupViewModel.isCreating.value = false

    }
}