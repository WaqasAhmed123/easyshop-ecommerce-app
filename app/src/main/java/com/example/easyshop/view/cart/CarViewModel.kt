package com.example.easyshop.view.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.easyshop.model.ProductModel
import com.example.easyshop.view.cart.CartViewModel.cartProducts

object CartViewModel {

    val cartProducts: MutableList<MutableList<Any>> = mutableListOf()
//    val cartProducts: MutableMap<ProductModel, Int> = mutableMapOf(
//    )

    fun incrementQuantity(index: Int) {
        val updatedQuantity = (cartProducts[index][1] as Int) + 1
        cartProducts[index][1] = updatedQuantity
    }

//    fun incrementQuantity(product: ProductModel) {
//        val currentQuantity = cartProducts[product]
//            ?: 0 // Get the current quantity, defaulting to 0 if the product is not found
//        val updatedQuantity = currentQuantity + 1 // Increment the quantity
//        cartProducts[product] = updatedQuantity // Update the quantity in the map
//    }


    fun decrementQuantity(index: Int) {
        val currentQuantity = (cartProducts[index][1] as Int)
        if (currentQuantity != 1) {
            val updatedQuantity = currentQuantity - 1 // Increment the quantity
            cartProducts[index][1] = updatedQuantity
        }

    }
//    fun decrementQuantity(product: ProductModel) {
//        val currentQuantity = cartProducts[product] ?: 0
//        if (currentQuantity != 1) {
//            val updatedQuantity = currentQuantity - 1 // Increment the quantity
//            cartProducts[product] = updatedQuantity
//        }
//
//    }


    fun deleteProduct(index: Int) {
        cartProducts.removeAt(index)
//        val currentQuantity =
//            cartProducts[productIndex].firstOrNull { it.first == "quantity" }?.second as? Int ?: 0
//        cartProducts[productIndex] = cartProducts[productIndex].map {
//            if (it.first == "quantity") Pair(
//                "quantity",
//                currentQuantity + 1
//            ) else it

    }
}