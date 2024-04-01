package com.example.easyshop.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun addProductToCart(cartItemLocal: CartItem)

    //        @Delete
//        suspend fun deleteProductInCart(cartItemLocal: CartItem)
    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun deleteProductInCart(productId: Int)

    @Query("SELECT * FROM cart_items")
    fun getCartProducts(): Flow<List<CartItem>>

    @Query("UPDATE cart_items SET quantity = quantity - 1 WHERE productId = :productId AND quantity > 1")
    suspend fun decrementProductQuantity(productId: Int)

    @Query("UPDATE cart_items SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun incrementProductQuantity(productId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}