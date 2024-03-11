package com.example.auth.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.auth.composables.SubmitButton
import com.example.auth.service.FirebaseService
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavController) {
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Welcome to my app",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.run { height(20.dp) })
            SubmitButton(
                onClick = {
                    scope.launch {
                        FirebaseService.signOut(navController=navController)


                    }

                },
                buttonTitle = "Logout",
                isLoading = HomeViewModel.isSigningOut
            )

        }
    }
}