package com.example.easyshop

import SharedPreferenceService
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshop.model.LoginRequest
import com.example.easyshop.repository.ProductsRepository
import com.example.easyshop.view.profile.ProfileView
import com.example.easyshop.service.FirebaseService
import com.example.easyshop.ui.theme.AuthTheme
import com.example.easyshop.view.TabScreen
import com.example.easyshop.view.selected_category_products.SelectedProductsView
import com.example.easyshop.view.cart.CartView
import com.example.easyshop.view.home.HomeView
import com.example.easyshop.view.login.LoginView
import com.example.easyshop.view.product_description.ProductDescriptionView
import com.example.easyshop.view.search.SearchViewScreen
import com.example.easyshop.view.signup.SignupView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseService.auth = Firebase.auth
        ProductsRepository.getAllProductsFromApi()
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
    val navController = rememberNavController()
    val initialScreenRoute = remember { mutableStateOf("login_view") }
//        initialScreenRoute.value = "tab_view"
//    initialScreenRoute.value = "product_description_view"
    if (SharedPreferenceService.hasToken()) {
        initialScreenRoute.value = "tab_view"

    } else {
        initialScreenRoute.value = "login_view"
    }

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
