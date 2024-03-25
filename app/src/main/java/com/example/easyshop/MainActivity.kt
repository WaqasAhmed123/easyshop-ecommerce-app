package com.example.easyshop

import SharedPreferenceService
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshop.repository.ProductsRepository
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.service.PermissionsService
import com.example.easyshop.ui.theme.AuthTheme
import com.example.easyshop.view.TabScreen
import com.example.easyshop.view.cart.CartView
import com.example.easyshop.view.home.HomeView
import com.example.easyshop.view.login.LoginView
import com.example.easyshop.view.map.MapView
import com.example.easyshop.view.product_description.ProductDescriptionView
import com.example.easyshop.view.profile.ProfileView
import com.example.easyshop.view.search.SearchViewScreen
import com.example.easyshop.view.selected_category_products.SelectedProductsView
import com.example.easyshop.view.signup.SignupView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
        FirebaseService.auth = Firebase.auth
        FirebaseInAppMessaging.getInstance().isAutomaticDataCollectionEnabled = true
//        CoroutineScope().launch {
//        PermissionsService.fetchCurrentLocation(context = this)

//        }
        //        LaunchedEffect(key1 = Unit, block = {
//            PermissionsService.RequestNotificationPermissionDialog()
//
//        })
//        LaunchedEffect(key1 = Unit, block = {
//            ProductsRepository.getAllProductsFromApi()
//
//        }

//        )
        ProductsRepository.getAllCategoriesFromApi()
//        val scope = rememberCoroutineScope()
//        scope.launch {
//        ProductsRepository.login(loginCredentials = LoginRequest(username = "mor_2314", password = "83r5^_"))
//
//        }

//        ProductsRepository.getProductsByCategoryFromApi("jewelery")
//        val FirebaseService.currentUser = FirebaseService.auth.currentUser

        setContent {
            AuthTheme {
                App()

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
//    LaunchedEffect(key1 = Unit) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        PermissionsService.RequestNotificationPermissionDialog()
//    }
    println("-------------------------------------------------------------------")
//    LaunchedEffect(Unit) {
//        try {
//            val token = Firebase.messaging.token.await()
//            Log.d("FCM token:", token)
//        } catch (e: Exception) {
//            Log.e("FCM token fetching error", e.message ?: "Unknown error")
//        }
//    }
//    }

    val navController = rememberNavController()
    val initialScreenRoute = remember { mutableStateOf("login_view") }
//        initialScreenRoute.value = "map_view"
    initialScreenRoute.value = "product_description_view"
    if (SharedPreferenceService.hasToken()) {
        initialScreenRoute.value = "tab_view"

    } else {
        initialScreenRoute.value = "login_view"
    }

    NavHost(enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
        )
    }, popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, tween(1000)
        )
    }, popExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, tween(1000)
        )
    },
//        enterTransition = { slideInHorizontally {  } (animationSpec = tween(700)) },
//        exitTransition = { fadeOut(animationSpec = tween(700)) },
//        popEnterTransition = enterTransition,
//        popExitTransition = exitTransition,
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

            SearchViewScreen(navController)
        }
        composable(route = "profile_view") {

            ProfileView(navController)
        }
        composable(route = "selected_products_view") {

            SelectedProductsView(navController)
        }
        composable(route = "cart_view") {

            CartView(navController)
        }
        composable(route = "product_description_view") {

            ProductDescriptionView(navController)
        }
        composable(route = "map_view") {

            MapView(navController)
        }
//        composable(route = "product_description_view/{productId}",
//            arguments = listOf(navArgument(name = "productId") {
//                type = NavType.IntType
//            })) {
//            val productId = it.arguments!!.getInt("productId")
//
//            ProductDescriptionView(
//                productId = productId, navController = navController
//
//            )
//        }
    }
}
