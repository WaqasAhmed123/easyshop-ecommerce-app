package com.example.easyshop.view.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.InputField
import com.example.easyshop.composables.SubmitButton
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.util.EmailAndPasswordValidator
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupView(navController: NavController) {
    val mContext = LocalContext.current
    var scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
//    val scrollState = rememberScrollState()


    Scaffold(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box {
                Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
//                    .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.signup),
                        contentDescription = null,
                        modifier = Modifier
                            .height(198.dp) // Set height
//                        .fillMaxHeight(0.4f) // Set height
                            .width(336.dp)

                    )
//                Text(text = "Enter Email:", style = TextStyle(fontSize = 18.sp))
                    Text(text = "Enter Email:", style = MaterialTheme.typography.bodyLarge)
                    InputField(
                        inputText = SignupViewModel.email,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Enter Password:", style = MaterialTheme.typography.bodyLarge)
                    InputField(
                        inputText = SignupViewModel.password, showTrailingIcon = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Confirm Password:", style = MaterialTheme.typography.bodyLarge)
                    InputField(
                        inputText = SignupViewModel.confirmPassword, showTrailingIcon = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SubmitButton(onClick = {
                            focusManager.clearFocus()
                            if (SignupViewModel.email.value.isEmpty() and SignupViewModel.password.value.isNotEmpty()) {
                                Toast.makeText(mContext, "Username is Empty", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (SignupViewModel.password.value.isEmpty() and SignupViewModel.email.value.isNotEmpty()) {
                                Toast.makeText(mContext, "Password is Empty", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (SignupViewModel.email.value.isEmpty() and SignupViewModel.password.value.isEmpty()) {
                                Toast.makeText(
                                    mContext, "Username and Password are Empty", Toast.LENGTH_SHORT
                                ).show()
                            } else if (SignupViewModel.password.value != SignupViewModel.confirmPassword.value) {
                                Toast.makeText(
                                    mContext, "Password doesn't match", Toast.LENGTH_SHORT
                                ).show()

                            } else if (!EmailAndPasswordValidator.isValidPassword(
                                    password = SignupViewModel.password.value, context = mContext
                                )
                            )
                            else {

                                scope.launch {
//                                val userCreated = FirebaseService.addUser(
//                                    FirebaseService.addUser(
//                                        email = SignupViewModel.email.value,
//                                        password = SignupViewModel.password.value,
//                                        context = mContext,
//                                        navController = navController
//                                    )
                                    SignupViewModel.email.value=""
                                    SignupViewModel.password.value=""
                                    SignupViewModel.confirmPassword.value=""


//                                if (userCreated) {
//                                }

                                }
                            }
                        }, buttonTitle = "Signup", isLoading = SignupViewModel.isCreating)
                    }
                    TextButton(
                        onClick = {
                            focusManager.clearFocus()
                            navController.popBackStack()
//                        navController.navigate("login_view") {
//                            popUpTo(navController.graph.startDestinationId) {
//                                inclusive = true
//                            }
//                        }


//                        navController.navigate("login_view")
                            /* Do something when button is clicked */
                        }, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Already have an Account? Login",
//                        color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                }
            }
        }

    }
}