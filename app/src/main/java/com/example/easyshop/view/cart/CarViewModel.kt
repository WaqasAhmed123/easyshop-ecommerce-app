package com.example.easyshop.view.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel:ViewModel() {

    val cartProducts = mutableStateListOf<List<Any>>()

    fun incrementQuantity(index: Int) {
        val updatedQuantity = (cartProducts[index][1] as Int) + 1
        cartProducts[index] = cartProducts[index].toMutableList().apply {
            set(1, updatedQuantity)
        }
    }

    fun decrementQuantity(index: Int) {
        val currentQuantity = (cartProducts[index][1] as Int)
        if (currentQuantity != 1) {
            val updatedQuantity = currentQuantity - 1
            cartProducts[index] = cartProducts[index].toMutableList().apply {
                set(1, updatedQuantity)
            }
        }
    }

    fun deleteProduct(index: Int) {
        cartProducts.removeAt(index)
    }
}
