package com.example.auth.view.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.auth.composables.InputField
import com.example.auth.composables.SubmitButton
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.auth.service.FirebaseService
import com.example.auth.view.signup.SignupViewModel
import kotlinx.coroutines.launch

//class LoginView {
//}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavController) {
    val mContext = LocalContext.current
    var scope = rememberCoroutineScope()



    Scaffold(modifier = Modifier.padding(16.dp)) {
        Box {
            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
            ) {
//                Text(text = "Enter Email:", style = TextStyle(fontSize = 18.sp))
                Text(text = "Enter Email:", style = MaterialTheme.typography.bodyLarge)
                InputField(
                    inputText = LoginViewModel.email,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Enter Password:", style = MaterialTheme.typography.bodyLarge)
                InputField(
                    inputText = LoginViewModel.password,
                    showTrailingIcon = true
                )
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubmitButton(onClick = {
                        if (LoginViewModel.email.value.isEmpty() and LoginViewModel.password.value.isNotEmpty()) {
                            Toast.makeText(mContext, "Username is Empty", Toast.LENGTH_SHORT).show()
                        } else if (LoginViewModel.password.value.isEmpty() and LoginViewModel.email.value.isNotEmpty()) {
                            Toast.makeText(mContext, "Password is Empty", Toast.LENGTH_SHORT).show()
                        } else if (LoginViewModel.email.value.isEmpty() and LoginViewModel.password.value.isEmpty()) {
                            Toast.makeText(
                                mContext,
                                "Username and Password are Empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            scope.launch {
//                                val userCreated = FirebaseService.addUser(
                                FirebaseService.login(
                                    email = LoginViewModel.email.value,
                                    password = LoginViewModel.password.value,
                                    context = mContext
                                )
//                                Toast.makeText(
//                                    mContext, "User Successfully registered", Toast.LENGTH_SHORT
//                                ).show()
                                navController.navigate("home_view")
//                                if (userCreated) {
//                                }

                            }
                        }
//                        if (mUsername.value.isNotEmpty() and mPassword.value.isNotEmpty()) {
//                            Toast.makeText(mContext, "Successfully Validated", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                    }, "Login", isLoading = LoginViewModel.isLoggingIn)
                }
                TextButton(
                    onClick = {

                        navController.navigate("signup_view")
                        /* Do something when button is clicked */
                    },
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(
                        "Don't have an Account? SignUp",
//                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }
    }
}