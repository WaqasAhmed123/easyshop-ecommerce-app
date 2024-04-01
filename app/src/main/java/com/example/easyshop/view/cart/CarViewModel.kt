package com.example.easyshop.view.cart

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.model.ProductModel
import com.example.easyshop.model.Rating
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.room_db.CartItem
import com.example.easyshop.room_db.RoomInstance
import com.example.easyshop.service.ProtoDataStoreService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


class CartViewModel() : ViewModel() {
    var isDataLoading = mutableStateOf(false)

    val cartDao = RoomInstance.db?.dao

        val cartProducts: StateFlow<List<CartItem>>? = cartDao?.getCartProducts()?.conflate()
        ?.stateIn(viewModelScope, SharingStarted.Eagerly,emptyList())
//    private val _cartProducts = MutableStateFlow<List<CartItem>>(emptyList())
//    val cartProducts: StateFlow<List<CartItem>> = _cartProducts

    suspend fun addProductToDb(cartItemLocal: CartItem) {
        cartDao?.addProductToCart(cartItemLocal)
    }

    suspend fun deleteProductInDb(productId: Int) {
        cartDao?.deleteProductInCart(productId)
    }

    fun incrementQuantity(productId: Int) {
        viewModelScope.launch {
            cartDao?.incrementProductQuantity(productId)
        }
    }

    fun decrementQuantity(productId: Int) {
        viewModelScope.launch {
            cartDao?.decrementProductQuantity(productId)
        }
    }

    init {
        viewModelScope.launch {
//            isDataLoading.value = true
//            RoomInstance.db?.dao?.getCartProducts()?.collect { products ->
//                _cartProducts.value = products
//                isDataLoading.value = false // Set  loading state to false once data is fetched
            }
        }
    }



