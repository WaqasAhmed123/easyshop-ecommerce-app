package com.example.easyshop.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun addProductToCart(cartItemLocal: CartItemLocal)
    @Delete
    suspend fun deleteProductInCart(cartItemLocal: CartItemLocal)

    @Query("SELECT * FROM cart_items")
    fun getCartProducts(): Flow<List<CartItemLocal>>
}