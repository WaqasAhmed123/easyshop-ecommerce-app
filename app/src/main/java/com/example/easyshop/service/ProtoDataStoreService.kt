package com.example.easyshop.service

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.easyshop.model.ProductModel
import com.example.easyshop.model.Rating
import com.example.easyshop.service.UserCartSerializer.userInfoDataStore
import com.example.easyshop.view.cart.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ProtoDataStoreService {

    suspend fun getSavedCartItem(context: Context): CartItem {
        return withContext(Dispatchers.IO) {
            val userInfo = context.userInfoDataStore.data.firstOrNull()
            if (userInfo == null) {
                CartItem(
                    product = ProductModel(
                        id = 1,
                        title = "dfhk",
                        price = 34,
                        description = "afg",
                        category = "afs",
                        image = "afh",
                        rating = Rating(rate = 4.00, count = 35)
                    ),
                    quantity = mutableStateOf(2)
                )
//                CartItem(
//                    nickname = "", emailAddress = ""
//                )
            }
            val retrievedInfoString = userInfo!!.CartItem
            try {
                val retrievedInfo = Json.decodeFromString<CartItem>(retrievedInfoString)
                retrievedInfo
//                println(retrievedInfo)
            } catch (_: Exception) {
                CartItem(
                    product = ProductModel(
                        id = 1,
                        title = "dfhk",
                        price = 34,
                        description = "afg",
                        category = "afs",
                        image = "afh",
                        rating = Rating(rate = 4.00, count = 35)
                    ),
                    quantity = mutableStateOf(2)
                )
            }

        }
    }

//    suspend fun savedCartItem(newInfo: CartItem, context: Context) {
//        val statusJson = Json.encodeToString(newInfo)
//
//        withContext(Dispatchers.IO) {
//            try {
//                context.userInfoDataStore.updateData { currentUser: MyUserInfo ->
//                    currentUser.toBuilder().setCartItem(statusJson).build()
//                }
//            } catch (e: Exception) {
//                Log.e("Error", "Error writing to proto store: $e")
//                throw e
//            }
//
//        }
//    }
}