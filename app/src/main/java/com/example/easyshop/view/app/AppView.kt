package com.example.easyshop.view.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshop.view.cart.CartView
import com.example.easyshop.view.cart.CartViewModel
import com.example.easyshop.view.home.HomeView
import com.example.easyshop.view.home.HomeViewModel
import com.example.easyshop.view.login.LoginView
import com.example.easyshop.view.login.LoginViewModel
import com.example.easyshop.view.map.MapView
import com.example.easyshop.view.map.MapViewModel
import com.example.easyshop.view.product_description.ProductDescriptionView
import com.example.easyshop.view.product_description.ProductDescriptionViewModel
import com.example.easyshop.view.profile.ProfileView
import com.example.easyshop.view.profile.ProfileViewModel
import com.example.easyshop.view.search.SearchViewModel
import com.example.easyshop.view.search.SearchViewScreen
import com.example.easyshop.view.selected_category_products.SelectedProductsView
import com.example.easyshop.view.selected_category_products.SelectedProductsViewModel
import com.example.easyshop.view.signup.SignupView
import com.example.easyshop.view.tab_view.TabScreen
import com.example.easyshop.view.tab_view.TabScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppView() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val tabScreenViewModel = viewModel<TabScreenViewModel>()
    val selectedProductsViewModel = viewModel<SelectedProductsViewModel>()
    val cartViewModel = viewModel<CartViewModel>()
    val productDescriptionViewModel =
        viewModel<ProductDescriptionViewModel>(factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductDescriptionViewModel(cartViewModel = cartViewModel) as T
            }
        })
    val appViewModel = viewModel<AppViewModel>(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppViewModel(context = context) as T
        }
    })
    val homeViewModel = viewModel<HomeViewModel>()
    val loginViewModel = viewModel<LoginViewModel>()
    val searchViewModel = viewModel<SearchViewModel>()
    val profileViewModel = viewModel<ProfileViewModel>()
    val mapViewModel = viewModel<MapViewModel>()
//    val appViewModel = viewModel<AppViewModel>()
//    LaunchedEffect(key1 = Unit) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        PermissionsService.RequestNotificationPermissionDialog()
//    }
//    println("-------------------------------------------------------------------")
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
//    initialScreenRoute.value = "map_view"
//    scope.launch {
//
//    }
//    LaunchedEffect(key1 = Unit) {
////        withContext(Dispatchers.Main) {
//            if (DataStoreService.hasToken(context = context)) {
//                initialScreenRoute.value = "tab_view"
//
//            } else {
//                initialScreenRoute.value = "login_view"
//            }
//
//
////        }
//
//

//    LaunchedEffect(Unit) {
//        runBlocking(Dispatchers.Main) {
//        }

    if (appViewModel.hasToken.value) {
        initialScreenRoute.value = "tab_view"
    } else {
        initialScreenRoute.value = "login_view"
    }

//        }
//        val hasToken = withContext(Dispatchers.IO) {

//    }
    if (!appViewModel.checkingToken.value) {

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
                LoginView(navController, loginViewModel = loginViewModel)
            }
            composable(route = "tab_view") {

                TabScreen(
                    navController,
                    homeViewModel = homeViewModel,
                    tabScreenViewModel = tabScreenViewModel,
                    productDescriptionViewModel = productDescriptionViewModel,
                    cartViewModel = cartViewModel,
                    searchViewModel = searchViewModel,
                    profileViewModel = profileViewModel

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
                    navController,
                    productDescriptionViewModel = productDescriptionViewModel,
                    searchViewModel = searchViewModel
                )
            }
            composable(route = "profile_view") {

                ProfileView(navController, profileViewModel = profileViewModel)
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

                MapView(navController, mapViewModel = mapViewModel)
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
//    }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

        }
    }
}

