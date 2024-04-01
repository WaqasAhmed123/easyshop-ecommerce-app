package com.example.easyshop.service

import com.example.easyshop.room_db.CartItem
import com.example.easyshop.service.UserCartSerializer.userInfoDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ProtoDataStoreService {

//    val cartDataStore:DataStore<UserCart>

//    suspend fun getRecentLocations(context: Context): MutableStateFlow<List<CartItem>> {
//        val cartItemsFlow = MutableStateFlow<List<CartItem>>(emptyList())
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
//                        CartItem(
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


//    suspend fun getSavedCartItems(context: Context): MutableStateFlow<List<CartItem>> {
//        val cartItemLocalFlow = MutableStateFlow<List<CartItem>>(emptyList())
//
//        withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo == null) {
//                println("Return empty list if no data is found")
//            } else {
//                val decodedList = mutableListOf<CartItem>()
//                userInfo.cartProductsList.forEach { cartItemJson ->
//                    try {
//                        val decodedItem = Json.decodeFromString<List<CartItem>>(cartItemJson.toString())
//                        decodedList.addAll(decodedItem)
//                    } catch (e: Exception) {
//                        // Handle decoding exceptions here
//                        println("Error decoding JSON: $e")
//                    }
//                }
//                println("Saved list is $decodedList")
//                cartItemLocalFlow.value = decodedList
//            }
//        }
//
//        return cartItemLocalFlow
//    }

//    suspend fun getSavedCartItems(context: Context): List<CartItem> {
//        return withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo == null) {
//                println("Return empty list if no data is found")
//                emptyList()
//            } else {
//                val decodedList = mutableListOf<CartItem>()
////                val books = Json.decodeFromString<List<CartItem>>(userInfo)
//
//                userInfo.cartProductsList.forEach { cartItemJson ->
//                    try {
//                        val decodedItem = Json.decodeFromString<List<CartItem>>(cartItemJson)
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


//    suspend fun getSavedCartItems(context: Context): List<CartItem> {
//        val decodedList: List<CartItem> = emptyList()
//        return withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo == null) {
//                println(" Return empty list if no data is found")
//                emptyList()
//            } else {
//                userInfo.cartProductsList.mapNotNull { cartItemJson ->
//                    try {
//                        Json.decodeFromString<CartItem>(cartItemJson).let {
//                            decodedList
//                        }
//                        Json.decodeFromString<CartItem>(cartItemJson)
//                    } catch (e: Exception) {
//                        null
//                    }
//                }
//                // Deserialize the cart items from JSON string
////                val cartItemsJson = userInfo.cartProductsList
////                val cartItemsJson = userInfo.cartProductsList
////                val cartProductsObt=Json.decodeFromString<List<CartItem>>(cartItemsJson)
////                cartItemsJson
////                cartItemsJson?.let {cartItem->
////                    Json.decodeFromString<List<CartItem>>(
////
////                    )
////                } ?: emptyList()
//            }
//        }
//    }

//    suspend fun saveCartItems(cartItems: List<CartItem>, context: Context) {
//    suspend fun saveCartItems(cartItems: List<CartItem>, context: Context) {
//        withContext(Dispatchers.IO) {
//            // Serialize the cart items to JSON string
//            println("saving cartitems")
//            val cartItemsJson = Json.encodeToString(cartItems)
//            println("saved $cartItemsJson")
//
//            // Save the JSON string to data store
//            context.userInfoDataStore.updateData { currentUser ->
//                currentUser.toBuilder().addCartProducts(cartItemsJson).build()
//            }
//        }
//    }

//    suspend fun getSavedCartItems(context: Context): MutableStateFlow<List<CartItem>> {
//        val cartItemLocalFlow = MutableStateFlow<List<CartItem>>(emptyList())
//
//        withContext(Dispatchers.IO) {
//            val userInfo = context.userInfoDataStore.data.firstOrNull()
//            if (userInfo != null) {
//                val decodedList = mutableListOf<CartItem>()
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
//                        val cartItemLocal = CartItem(productModel, cartProduct.quantity)
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

//    suspend fun saveCartItems(cartItem: CartItem, context: Context) {
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


