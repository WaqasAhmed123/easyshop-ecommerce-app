package com.example.easyshop.view.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.model.BottomNavigationItemsData
import com.example.easyshop.repository.ProductsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
    val allProducts = ProductsRepository.allProductsList
    var allCategoriesList = ProductsRepository.allCategories

    var isDataLoaded = mutableStateOf(false)
    var isDescProducts = mutableStateOf(false)

    init {
        viewModelScope.launch {

            ProductsRepository.getAllCategoriesFromApi()
            ProductsRepository.getAllProductsFromApi(isDesc = isDescProducts.value)
            println("func executed data is ${allCategoriesList.size}")
            isDataLoaded.value = true


        }
//        ProductsRepository.getAllProductsFromApi(isDesc = homeViewModel.isDescProducts.value)

    }


}