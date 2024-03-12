package com.example.auth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auth.view.profile.ProfieView
import com.example.auth.service.FirebaseService
import com.example.auth.ui.theme.AuthTheme
import com.example.auth.view.TabScreen
import com.example.auth.view.home.HomeView
import com.example.auth.view.login.LoginView
import com.example.auth.view.search.SearchViewClass
import com.example.auth.view.signup.SignupView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseService.auth = Firebase.auth
//        val FirebaseService.currentUser = FirebaseService.auth.currentUser

        setContent {
            AuthTheme {
                // A surface container using the 'background' color from the theme
                        App()
    //                Surface(
//                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                )
//                {
////                    LoginView()
////                    SignupView()
////                    Greeting("Android")
//                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
    val navController = rememberNavController()
    val initialScreenRoute = remember { mutableStateOf("login_view") }
//    if (FirebaseService.auth.currentUser != null) {
        initialScreenRoute.value = "tab_view"

//    }
//    else{
//        initialScreenRoute.value="login_view"
//    }

    NavHost(
//        navController = navController, startDestination = "login_view"
        navController = navController, startDestination = initialScreenRoute.value
    ) {
        composable(route = "login_view") {
            LoginView(navController)
        }
        composable(route = "tab_view") {
            TabScreen(navController)
        }
        composable(route = "signup_view") {

            SignupView(navController)
        }
        composable(route = "home_view") {

            HomeView(navController)
        }
        composable(route = "search_view") {

            SearchViewClass(navController)
        }
        composable(route = "profile_view") {

            ProfieView(navController)
        }
    }
}
