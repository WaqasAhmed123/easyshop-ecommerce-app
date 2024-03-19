package com.example.easyshop.view.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.mutableStateOf
import com.example.easyshop.model.BottomNavigationItemsData
import com.example.easyshop.model.UserModel
import com.example.easyshop.repository.ProductsRepository

object HomeViewModel {
    @SuppressLint("MutableCollectionMutableState")
//    val allProducts = mutableStateOf(ProductsRepository.allProductsList)
    val allProducts = ProductsRepository.allProductsList
    var isSigningOut = mutableStateOf(false)
//    var userName = mutableStateOf("Waqas")
    var userName =UserModel().userName
    var search = mutableStateOf("")
    val itemsPrice: MutableList<String> = mutableListOf("$40", "\$40", "\$40","$40", "\$40", "\$40","$40", "\$40", "\$40")
    val itemsName: MutableList<String> = mutableListOf("Watch", "Nike", "Bat","Watch", "Nike", "Bat","Watch", "Nike", "Bat")
    val bottomNavItems= listOf<BottomNavigationItemsData>(
        BottomNavigationItemsData("home_view", icon =Icons.Default.Home),
        BottomNavigationItemsData("Search_view", icon =Icons.Default.Search),
        BottomNavigationItemsData("cart_view", icon =Icons.Default.ShoppingBag),
        BottomNavigationItemsData("profile_view", icon =Icons.Default.Person)
    )



}