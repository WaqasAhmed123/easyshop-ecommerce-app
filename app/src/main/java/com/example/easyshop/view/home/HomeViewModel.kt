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
import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.repository.UserRepository.userName
import com.example.easyshop.room_db.CartItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
//    val cartProducts: StateFlow<List<ProductModel>>? = cartDao?.getCartProducts()?.conflate()
//        ?.stateIn(viewModelScope, SharingStarted.Eagerly,emptyList())
    var allProducts = ProductsRepository.allProductsFlow.asStateFlow()

//    val allProducts = ProductsRepository.allProductsList
    var allCategoriesList = ProductsRepository.allCategories
    val userName: StateFlow<String> = UserRepository.userName

    var isDataLoaded = mutableStateOf(false)
    var isDescProducts = mutableStateOf(false)

    init {
        viewModelScope.launch {

            ProductsRepository.getAllCategoriesFromApi()
            isDataLoaded.value=true
            ProductsRepository.getAllProductsFromApi(isDesc = isDescProducts.value)
            println("func executed data is ${allCategoriesList.size}")
//            isDataLoaded.value = true
            println("user name in user repo is ${UserRepository.userName.value}")
            println("user name in viewModel is is ${userName.value}")


        }
//        ProductsRepository.getAllProductsFromApi(isDesc = homeViewModel.isDescProducts.value)

    }


}