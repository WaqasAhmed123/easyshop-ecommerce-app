package com.example.easyshop.room_db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.easyshop.model.ProductModel


@Entity(
    tableName = "cart_items"
//    tableName = "cart_items",
//    foreignKeys = [ForeignKey(
//        entity = ProductModel::class,
//        parentColumns = ["id"],
//        childColumns = ["productId"],
//        onDelete = ForeignKey.CASCADE
//    )]
//    tableName = "cart_items",
//    primaryKeys = ["productId"],
//    foreignKeys = [ForeignKey(
//        entity = ProductModel::class,
//        parentColumns = ["id"],
//        childColumns = ["productId"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class CartItem(
    @PrimaryKey(autoGenerate = false)
    val productId: Int,
    @Embedded
    val product: ProductModel, // Assuming ProductModel is your product data class
    var quantity: Int
//    var quantity: Int
)