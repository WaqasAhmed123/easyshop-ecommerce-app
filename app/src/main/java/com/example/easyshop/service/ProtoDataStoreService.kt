package com.example.easyshop.service

import android.content.Context
import android.util.Log
import com.example.easyshop.service.UserCartSerializer.userInfoDataStore
import com.example.easyshop.view.cart.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object ProtoDataStoreService {

    suspend fun getSavedCartItem(context: Context): CartItem {
        return withContext(Dispatchers.IO) {
            val userInfo = context.userInfoDataStore.data.firstOrNull()
            if (userInfo == null) {
//                CartItem(
//                    nickname = "", emailAddress = ""
//                )
            }
            val retrievedInfoString = userInfo!!.CartItem
            try {
                val retrievedInfo = Json.decodeFromString<CartItem>(retrievedInfoString)
                retrievedInfo
            } catch (_: Exception) {
                CartItem(
                    nickname = "", emailAddress = ""
                )
            }

        }
    }

    suspend fun savedCartItem(newInfo: CartItem, context: Context) {
        val statusJson = Json.encodeToString(newInfo)

        withContext(Dispatchers.IO) {
            try {
                context.userInfoDataStore.updateData { currentUser: MyUserInfo ->
                    currentUser.toBuilder().setCartItem(statusJson).build()
                }
            } catch (e: Exception) {
                Log.e("Error", "Error writing to proto store: $e")
                throw e
            }

        }
    }
}