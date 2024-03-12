package com.example.auth.view.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.auth.model.BottomNavigationItemsData

object HomeViewModel {
    var isSigningOut = mutableStateOf(false)
    var userName = mutableStateOf("Waqas")
    var search = mutableStateOf("")
    val itemsPrice: MutableList<String> = mutableListOf("$40", "\$40", "\$40")
    val itemsName: MutableList<String> = mutableListOf("Watch", "Nike", "Bat")
    val bottomNavItems= listOf<BottomNavigationItemsData>(
        BottomNavigationItemsData("home_view", icon =Icons.Default.Home),
        BottomNavigationItemsData("Search_view", icon =Icons.Default.Search),
        BottomNavigationItemsData("cart_view", icon =Icons.Default.ShoppingBag),
        BottomNavigationItemsData("profile_view", icon =Icons.Default.Person)
    )



}