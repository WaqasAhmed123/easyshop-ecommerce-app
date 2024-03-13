package com.example.auth.view.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.auth.R
import com.example.auth.composables.SubmitButton
import com.example.auth.model.UserModel
import com.example.auth.service.FirebaseService
import com.example.auth.view.home.HomeViewModel
import kotlinx.coroutines.launch
import textStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfieView(navController: NavController) {
    val scope = rememberCoroutineScope()
    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(

                painter = painterResource(id = R.drawable.signup),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    //                        .size(size)
                    .clip(CircleShape)// Clip the image to a circular shape
            )

            Text(
                text = "Welcome ${UserModel().userName}",
//                style = textStyle()["titleLarge"]!!,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W500),
//                textAlign = TextAlign.Center
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