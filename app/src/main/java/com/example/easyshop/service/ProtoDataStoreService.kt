package com.example.easyshop.service

import android.content.Context
import com.example.easyshop.model.ProductModel
import com.example.easyshop.model.Rating
import com.example.easyshop.proto.UserCart
import com.example.easyshop.service.UserCartSerializer.userInfoDataStore
import com.example.easyshop.view.cart.CartItemLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ProtoDataStoreService {

//    val cartDataStore:DataStore<UserCart>

//    suspend fun getRecentLocations(context: Context): MutableStateFlow<List<CartItemLocal>> {
//        val cartItemsFlow = MutableStateFlow<List<CartItemLocal>>(emptyList())
//
//        val dataStore: DataStore<UserCart> = context.userInfoDataStore
//
//        runBlocking {
//            dataStore.data
//                .catch { exception ->
//                    if (exception is InvalidProtocolBufferException) {
//                        // Handle parsing errors
//                        // For example, if no data is stored yet
//                        // You can log the error or handle it gracefully
//                        exception.printStackTrace()
//                    } else {
//                        throw exception
//                    }
//                }
//                .map { userCart ->
//                    userCart.cartProductsList.map { cartProduct ->
//                        CartItemLocal(
//                            product = cartProduct.product.toProductModel(),
//                            quantity = cartProduct.quantity
//                        )
//                    }
//                }
//                .collect { cartItems ->
//                    cartItemsFlow.value = cartItems
//                }
//        }
//
//        return cartItemsFlow
//    }


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
                        val decodedItem = Json.decodeFromString<List<CartItemLocal>>(cartItemJson.toString())
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

//    suspend fun saveCartItemLocals(cartItems: List<CartItemLocal>, context: Context) {
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

//    suspend fun getSavedCartItemLocals(context: Context): MutableStateFlow<List<CartItemLocal>> {
//        val cartItemLocalFlow = MutableStateFlow<List<CartItemLocal>>(emptyList())
//
//        withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo != null) {
//                val decodedList = mutableListOf<CartItemLocal>()
//                userInfo.cartProductsList.forEach { cartProduct ->
//                    try {
//                        val product = cartProduct.product
//                        val rating = product.rating
//                        val productModel = ProductModel(
//                            id = product.id,
//                            title = product.title,
//                            price = product.price,
//                            description = product.description,
//                            category = product.category,
//                            image = product.image,
//                            rating = Rating(rate = rating.rate, count = rating.count)
//                        )
//                        val cartItemLocal = CartItemLocal(productModel, cartProduct.quantity)
//                        decodedList.add(cartItemLocal)
//                    } catch (e: Exception) {
//                        // Handle decoding exceptions here
//                        println("Error decoding Protobuf: $e")
//                    }
//                }
//                println("Saved list is $decodedList")
//                cartItemLocalFlow.value = decodedList
//            }
//        }
//
//        return cartItemLocalFlow
//    }

//    suspend fun saveCartItemLocals(cartItem: CartItemLocal, context: Context) {
//        withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull() ?: UserCart.getDefaultInstance()
//
//            val updatedUserInfo = userInfo.toBuilder()
//                // Use the correct addCartProducts overload based on the type of cartProduct:
////                .addCartProducts(cartItem)  // If cartProduct is already built
//                 .addCartProducts(cartItem.product.toBuilder())  // If you need to modify cartProduct later
//                .build()
//
//            context.userInfoDataStore.updateData { updatedUserInfo }
//        }
//    }
}


