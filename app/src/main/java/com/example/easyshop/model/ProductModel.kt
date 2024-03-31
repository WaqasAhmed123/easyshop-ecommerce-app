package com.example.easyshop.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val id: Int,
    val title: String,
//    val price: Number,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

@Serializable
data class Rating(
    val rate: Double,
    val count: Int
)

// Define additional data classes or enums if needed
