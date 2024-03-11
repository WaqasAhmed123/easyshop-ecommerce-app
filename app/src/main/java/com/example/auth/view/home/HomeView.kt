package com.example.auth.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.auth.R
import com.example.auth.composables.SubmitButton
import com.example.auth.service.FirebaseService
import kotlinx.coroutines.launch
import textStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavController) {
    val scope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
        ) {
            Column {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(

                        painter = painterResource(id = R.drawable.signup),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            //                        .size(size)
                            .clip(CircleShape)// Clip the image to a circular shape
                    )
                    Column(modifier = Modifier.padding(horizontal = 10.dp)){
                        Text(
                            text = "Hello",
                            style = textStyle(textColor = Color.Gray)["bodySmall"]!!
                        )
                        Text(
                            text = "${HomeViewModel.userName.value}",
                            style = textStyle()["titleMedium"]!!
                        )

                    }
                    //                    Box(modifier = Modifier.fillMaxSize())
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable(onClick = {})
                            .clip(CircleShape)
                            .background(Color(0xFFF8F7F7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.NotificationsNone,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Text(
                    text = "Welcome to my app",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.run { height(20.dp) })
                SubmitButton(
                    onClick = {
                        scope.launch {
                            FirebaseService.signOut(navController = navController)


                        }

                    },
                    buttonTitle = "Logout",
                    isLoading = HomeViewModel.isSigningOut
                )

            }
        }
    }
}