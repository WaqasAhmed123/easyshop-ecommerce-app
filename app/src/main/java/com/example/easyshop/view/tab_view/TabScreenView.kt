package com.example.easyshop.view.tab_view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.easyshop.view.cart.CartView
import com.example.easyshop.view.cart.CartViewModel
import com.example.easyshop.view.home.HomeView
import com.example.easyshop.view.home.HomeViewModel
import com.example.easyshop.view.product_description.ProductDescriptionViewModel
import com.example.easyshop.view.profile.ProfileView
import com.example.easyshop.view.profile.ProfileViewModel
import com.example.easyshop.view.search.SearchViewModel
import com.example.easyshop.view.search.SearchViewScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    tabScreenViewModel: TabScreenViewModel,
    productDescriptionViewModel: ProductDescriptionViewModel,
    cartViewModel: CartViewModel,
    searchViewModel: SearchViewModel,
    profileViewModel: ProfileViewModel
) {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()


//    Scaffold(modifier = Modifier.padding(16.dp)) {
    Scaffold(
//        modifier = Modifier.padding(16.dp),
        bottomBar = {
            TabRow(
//                modifier = Modifier.swipeEnabled = false
//                tabs = ,
                indicator = {},
                backgroundColor = MaterialTheme.colorScheme.secondary,
                selectedTabIndex = selectedItemIndex
            ) {
                tabScreenViewModel.bottomNavItems.forEachIndexed { index, tabItem ->
                    Tab(selected = selectedItemIndex == index, onClick = {
                        selectedItemIndex = index
//                                  navController.navigate(HomeViewModel.bottomNavItems[index].route)
                    }, icon = {
                        Icon(
                            imageVector = tabScreenViewModel.bottomNavItems[index].icon,
                            contentDescription = null,
                            tint = if (selectedItemIndex == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color(0xFF9E9E9E)

                            }
                        )
                    }


                    )


                }

            }
        }) { innerPadding ->
        // Content based on selected index
        when (selectedItemIndex) {

            0 -> Box(modifier = Modifier.padding(innerPadding)) {
                HomeView(
                    navController = navController,
                    homeViewModel,
                    productDescriptionViewModel = productDescriptionViewModel
                )
            }

            1 -> Box(modifier = Modifier.padding(innerPadding)) {
                SearchViewScreen(
                    navController = navController,
                    productDescriptionViewModel = productDescriptionViewModel,
                    searchViewModel = searchViewModel
                )
            }

            2 -> Box(modifier = Modifier.padding(innerPadding)) {
                CartView(navController, cartViewModel = cartViewModel)
            }

            3

            -> Box(modifier = Modifier.padding(innerPadding)) {
                ProfileView(navController, profileViewModel = profileViewModel)

            }
        }
    }

}