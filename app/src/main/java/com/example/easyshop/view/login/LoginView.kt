package com.example.easyshop.view.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.InputField
import com.example.easyshop.composables.SubmitButton
import com.example.easyshop.model.LoginRequest
import com.example.easyshop.repository.ProductsRepository
import kotlinx.coroutines.launch

//class LoginView {
//}
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavController, loginViewModel: LoginViewModel) {
    val mContext = LocalContext.current
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current




    Scaffold(modifier = Modifier.padding(16.dp)) {
        Box() {
            Column(

//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = null,
                    modifier = Modifier
//                        .height(198.dp) // Set height
//                        .fillMaxHeight(0.4f) // Set height
                        .fillMaxHeight(0.4f) // Set height
//                        .width(336.dp)
                        .fillMaxWidth()

                )

//                Text(text = "Enter Email:", style = TextStyle(fontSize = 18.sp))
                Text(text = "Enter UserName/Email:", style = MaterialTheme.typography.bodyLarge)

                InputField(
//                    inputText = loginViewModel.userName,
//                    inputText = loginViewModel.userName, autofillNode = autofillNodeUserName
                    inputText = loginViewModel.userName, isUserName = true
                )
//                InputField(inputText = loginViewModel.userName, onNextFieldRequested = {
//                    focusManager.moveFocus(FocusDirection.Next)
//                })
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Enter Password:", style = MaterialTheme.typography.bodyLarge)
                InputField(
                    inputText = loginViewModel.password, showTrailingIcon = true
                )
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    SubmitButton(onClick = {
                        focusManager.clearFocus()
                        if (loginViewModel.userName.value.isEmpty() and loginViewModel.password.value.isNotEmpty()) {
                            Toast.makeText(mContext, "Username is Empty", Toast.LENGTH_SHORT).show()
                        } else if (loginViewModel.password.value.isEmpty() and loginViewModel.userName.value.isNotEmpty()) {
                            Toast.makeText(mContext, "Password is Empty", Toast.LENGTH_SHORT).show()
                        } else if (loginViewModel.userName.value.isEmpty() and loginViewModel.password.value.isEmpty()) {
                            Toast.makeText(
                                mContext, "Username and Password are Empty", Toast.LENGTH_SHORT
                            ).show()
                        }
//                        else if (!EmailAndPasswordValidator.isValidPassword(
//                                password = loginViewModel.password.value, context = mContext
//                            )
//                        )
                        else {
                            scope.launch {
                                loginViewModel.login(context = mContext, navController)
//                                val userCreated = FirebaseService.addUser(
//                                ProductsRepository.loginFromApi(
//                                    loginCredentials = LoginRequest(
//                                        username = loginViewModel.userName.value,
//                                        password = loginViewModel.password.value
//                                    ), context = mContext, navController = navController

//                                )
//                                FirebaseService.login(
//                                    userName = loginViewModel.userName.value,
//                                    password = loginViewModel.password.value,
//                                    context = mContext,
//                                    navController = navController
//                                )
//                                loginViewModel.userName.value = ""
//                                loginViewModel.password.value = ""
//                                Toast.makeText(
//                                    mContext, "User Successfully registered", Toast.LENGTH_SHORT
//                                ).show()
//                                navController.navigate("home_view")
//                                if (userCreated) {
//                                }

                            }
                        }
//                        if (mUsername.value.isNotEmpty() and mPassword.value.isNotEmpty()) {
//                            Toast.makeText(mContext, "Successfully Validated", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                    }, "Login", isLoading = loginViewModel.isLoggingIn)
                }
                TextButton(
                    onClick = {
                        focusManager.clearFocus()


                        navController.navigate("signup_view")/* Do something when button is clicked */
                        loginViewModel.userName.value = ""
                        loginViewModel.password.value = ""
                    }, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
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