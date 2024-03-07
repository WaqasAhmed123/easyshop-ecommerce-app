package com.example.auth.view.login

import android.annotation.SuppressLint
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

//class LoginView {
//}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(navController: NavController) {

    Scaffold(modifier = Modifier.padding(16.dp)) {
        Box {
            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
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


                Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()){
                    SubmitButton(onClick = { /*TODO*/ },"Login")
                }
                TextButton(
                    onClick = {
                        navController.navigate("signup_view")
                        /* Do something when button is clicked */
                    },
                    modifier = Modifier.align(alignment =  Alignment.CenterHorizontally)
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