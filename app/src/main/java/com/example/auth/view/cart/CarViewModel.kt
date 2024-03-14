package com.example.auth.view.cart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object CartViewModel {

    var cartProducts: MutableList<List<Pair<String, Any>>> = mutableStateListOf(
        listOf(
            Pair("productName", "bat"),
            Pair("brand", "rolex"),
            Pair("quantity", 2),
            Pair("price", "$40")
        ),
        listOf(
            Pair("productName", "watch"),
            Pair("brand", "rolex"),
            Pair("quantity", 2),
            Pair("price", "$40")
        ),
    )

    fun incrementQuantity(productIndex: Int) {
        val currentQuantity =
            cartProducts[productIndex].firstOrNull { it.first == "quantity" }?.second as? Int ?: 0
        cartProducts[productIndex] = cartProducts[productIndex].map {
            if (it.first == "quantity") Pair(
                "quantity",
                currentQuantity + 1
            ) else it
        }
    }

    fun decrementQuantity(productIndex: Int) {
        val currentQuantity =
            cartProducts[productIndex].firstOrNull { it.first == "quantity" }?.second as? Int ?: 0
        if (currentQuantity != 1) {
            cartProducts[productIndex] = cartProducts[productIndex].map {
                if (it.first == "quantity") Pair(
                    "quantity",
                    currentQuantity - 1
                ) else it
            }

        }
    }

    fun deleteProduct(productIndex: Int) {
        cartProducts.removeAt(productIndex)
//        val currentQuantity =
//            cartProducts[productIndex].firstOrNull { it.first == "quantity" }?.second as? Int ?: 0
//        cartProducts[productIndex] = cartProducts[productIndex].map {
//            if (it.first == "quantity") Pair(
//                "quantity",
//                currentQuantity + 1
//            ) else it

    }
}