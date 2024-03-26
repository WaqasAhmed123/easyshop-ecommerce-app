package com.example.easyshop

import SharedPreferenceService
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.ui.theme.AuthTheme
import com.example.easyshop.view.cart.CartView
import com.example.easyshop.view.cart.CartViewModel
import com.example.easyshop.view.home.HomeView
import com.example.easyshop.view.home.HomeViewModel
import com.example.easyshop.view.login.LoginView
import com.example.easyshop.view.map.MapView
import com.example.easyshop.view.product_description.ProductDescriptionView
import com.example.easyshop.view.product_description.ProductDescriptionViewModel
import com.example.easyshop.view.profile.ProfileView
import com.example.easyshop.view.search.SearchViewScreen
import com.example.easyshop.view.selected_category_products.SelectedProductsView
import com.example.easyshop.view.selected_category_products.SelectedProductsViewModel
import com.example.easyshop.view.signup.SignupView
import com.example.easyshop.view.tab_view.TabScreen
import com.example.easyshop.view.tab_view.TabScreenViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseService.auth = Firebase.auth
        FirebaseInAppMessaging.getInstance().isAutomaticDataCollectionEnabled = true
//        ProductsRepository.getAllCategoriesFromApi()

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
    val tabScreenViewModel = viewModel<TabScreenViewModel>()
    val selectedProductsViewModel = viewModel<SelectedProductsViewModel>()
    val cartViewModel = viewModel<CartViewModel>()
    val productDescriptionViewModel = viewModel<ProductDescriptionViewModel>(
        factory = object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductDescriptionViewModel(cartViewModel=cartViewModel) as T
            }
        }
    )
    val homeViewModel = viewModel<HomeViewModel>()
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

            TabScreen(
                navController,
                homeViewModel = homeViewModel,
                tabScreenViewModel = tabScreenViewModel,
                productDescriptionViewModel = productDescriptionViewModel,
                cartViewModel = cartViewModel

            )
        }
        composable(route = "signup_view") {

            SignupView(navController)
        }
        composable(route = "home_view") {


            HomeView(
                navController,
                homeViewModel = homeViewModel,
                productDescriptionViewModel = productDescriptionViewModel
            )
        }
        composable(route = "search_view") {


            SearchViewScreen(
                navController, productDescriptionViewModel = productDescriptionViewModel
            )
        }
        composable(route = "profile_view") {

            ProfileView(navController)
        }
        composable(route = "selected_products_view") {

            SelectedProductsView(
                navController,
                selectedProductsViewModel = selectedProductsViewModel,
                productDescriptionViewModel
            )
        }

        composable(route = "cart_view") {
            CartView(navController = navController, cartViewModel = cartViewModel)
        }
        composable(route = "product_description_view") {


            ProductDescriptionView(
                cartViewModel = cartViewModel,
                productDescriptionViewModel = productDescriptionViewModel,
                navController = navController
            )
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
