package com.example.easyshop.view.tab_view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.lifecycle.ViewModel
import com.example.easyshop.model.BottomNavigationItemsData

class TabScreenViewModel : ViewModel() {
    val bottomNavItems = listOf<BottomNavigationItemsData>(
        BottomNavigationItemsData("home_view", icon = Icons.Default.Home),
        BottomNavigationItemsData("Search_view", icon = Icons.Default.Search),
        BottomNavigationItemsData("cart_view", icon = Icons.Default.ShoppingBag),
        BottomNavigationItemsData("profile_view", icon = Icons.Default.Person)
    )

}