package com.example.easyshop.service

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.example.easyshop.view.home.HomeViewModel
import com.example.easyshop.view.login.LoginViewModel
import com.example.easyshop.view.signup.SignupViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.CompletableFuture

object FirebaseService {
    // Initialize Firebase Auth
    lateinit var auth: FirebaseAuth
    lateinit var currentUser: FirebaseAuth
//    val auth = Firebase.auth
//    var isSignupSeccessful=false

    //    fun addUser(email: String, password: String, context: Context): Boolean {
    fun addUser(email: String, password: String, context: Context, navController: NavController) {
        val completableFuture = CompletableFuture<Boolean>()
        SignupViewModel.isCreating.value = true
//        CoroutineScope(Dispatchers.IO).launch{
//
//        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context, "User Successfully registered", Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")`
                    println(
                        "user created"

                    )
                    val user = auth.currentUser
//                    isSignupSeccessful= true
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
                SignupViewModel.isCreating.value = false
            }
//        return  isSignupSeccessful

    }

    fun login(email: String, password: String, context: Context, navController: NavController) {
        LoginViewModel.isLoggingIn.value = true



        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    navController.navigate("tab_view") {
                        popUpTo("login_view") {
                            inclusive = true
                        }
                    }
//                    navController.navigate("tab_view")
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("login", "signInWithEmail:success")
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("login failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
                LoginViewModel.isLoggingIn.value = false

            }
    }

    fun signOut(navController: NavController) {
        HomeViewModel.isSigningOut.value = true
        auth.signOut()
        navController.navigate("login_view") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
        HomeViewModel.isSigningOut.value = false
    }

//    fun googleSignIn(credential: AuthCredential):Flow<Resource<AuthResult>>{
//        return Flow{
//            emit(Resource.Loading())
//        }
//    }
}