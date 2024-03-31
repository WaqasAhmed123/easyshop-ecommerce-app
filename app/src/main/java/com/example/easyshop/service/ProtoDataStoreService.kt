package com.example.easyshop.service

import android.content.Context
import com.example.easyshop.service.UserCartSerializer.userInfoDataStore
import com.example.easyshop.view.cart.CartItemLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ProtoDataStoreService {
    suspend fun getSavedCartItemLocals(context: Context): MutableStateFlow<List<CartItemLocal>> {
        val cartItemLocalFlow = MutableStateFlow<List<CartItemLocal>>(emptyList())

        withContext(Dispatchers.IO) {
            val userInfo = context.userInfoDataStore.data.firstOrNull()
            if (userInfo == null) {
                println("Return empty list if no data is found")
            } else {
                val decodedList = mutableListOf<CartItemLocal>()
                userInfo.cartProductsList.forEach { cartItemJson ->
                    try {
                        val decodedItem = Json.decodeFromString<List<CartItemLocal>>(cartItemJson)
                        decodedList.addAll(decodedItem)
                    } catch (e: Exception) {
                        // Handle decoding exceptions here
                        println("Error decoding JSON: $e")
                    }
                }
                println("Saved list is $decodedList")
                cartItemLocalFlow.value = decodedList
            }
        }

        return cartItemLocalFlow
    }

//    suspend fun getSavedCartItemLocals(context: Context): List<CartItemLocal> {
//        return withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo == null) {
//                println("Return empty list if no data is found")
//                emptyList()
//            } else {
//                val decodedList = mutableListOf<CartItemLocal>()
////                val books = Json.decodeFromString<List<CartItemLocal>>(userInfo)
//
//                userInfo.cartProductsList.forEach { cartItemJson ->
//                    try {
//                        val decodedItem = Json.decodeFromString<List<CartItemLocal>>(cartItemJson)
//                        decodedList.addAll(decodedItem)
//                    } catch (e: Exception) {
//                        // Handle decoding exceptions here
//                        println("Error decoding JSON: $e")
//                    }
//                }
//                println(
//                    "saved list is $decodedList"
//                )
//                decodedList // Return the list of successfully decoded items
//            }
//        }
//    }


//    suspend fun getSavedCartItemLocals(context: Context): List<CartItemLocal> {
//        val decodedList: List<CartItemLocal> = emptyList()
//        return withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo == null) {
//                println(" Return empty list if no data is found")
//                emptyList()
//            } else {
//                userInfo.cartProductsList.mapNotNull { cartItemJson ->
//                    try {
//                        Json.decodeFromString<CartItemLocal>(cartItemJson).let {
//                            decodedList
//                        }
//                        Json.decodeFromString<CartItemLocal>(cartItemJson)
//                    } catch (e: Exception) {
//                        null
//                    }
//                }
//                // Deserialize the cart items from JSON string
////                val cartItemsJson = userInfo.cartProductsList
////                val cartItemsJson = userInfo.cartProductsList
////                val cartProductsObt=Json.decodeFromString<List<CartItemLocal>>(cartItemsJson)
////                cartItemsJson
////                cartItemsJson?.let {cartItem->
////                    Json.decodeFromString<List<CartItemLocal>>(
////
////                    )
////                } ?: emptyList()
//            }
//        }
//    }

    suspend fun saveCartItemLocals(cartItems: List<CartItemLocal>, context: Context) {
        withContext(Dispatchers.IO) {
            // Serialize the cart items to JSON string
            println("saving cartitems")
            val cartItemsJson = Json.encodeToString(cartItems)
            println("saved $cartItemsJson")

            // Save the JSON string to data store
            context.userInfoDataStore.updateData { currentUser ->
                currentUser.toBuilder().addCartProducts(cartItemsJson).build()
            }
        }
    }
}


