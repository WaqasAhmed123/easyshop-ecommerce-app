package com.example.easyshop.view.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.easyshop.model.ProductModel


data class CartItem(
    val product: ProductModel, // Assuming ProductModel is your product data class
    var quantity: Int
)

class CartViewModel : ViewModel() {

    var cartProducts = mutableStateListOf<CartItem>()

    fun incrementQuantity(index: Int) {
        val item = cartProducts[index]
        item.quantity++
        cartProducts[index] = item
    }

    fun decrementQuantity(index: Int) {
        val item = cartProducts[index]
        if (item.quantity > 1) {
            item.quantity--
            cartProducts[index] = item
        }
    }

    fun deleteProduct(index: Int) {
        cartProducts.removeAt(index)
    }
}

//class CartViewModel:ViewModel() {
//
//    val cartProducts = mutableStateListOf<List<Any>>()
//
//    fun incrementQuantity(index: Int) {
//        val updatedQuantity = (cartProducts[index][1] as Int) + 1
//        cartProducts[index] = cartProducts[index].toMutableList().apply {
//            set(1, updatedQuantity)
//        }
//    }
//
//    fun decrementQuantity(index: Int) {
//        val currentQuantity = (cartProducts[index][1] as Int)
//        if (currentQuantity != 1) {
//            val updatedQuantity = currentQuantity - 1
//            cartProducts[index] = cartProducts[index].toMutableList().apply {
//                set(1, updatedQuantity)
//            }
//        }
//    }
//
//    fun deleteProduct(index: Int) {
//        cartProducts.removeAt(index)
//    }
//}
