package com.example.easyshop.model

data class ProductModel(
    val id: Int,
    val title: String,
    val price: Number,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)

// Define additional data classes or enums if needed
