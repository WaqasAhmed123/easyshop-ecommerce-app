package com.example.easyshop.util

import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository

object CommonFunctions {
    fun findProductById(id: Int): ProductModel? {
        return ProductsRepository.allProductsList.firstOrNull { product ->
            product.id == id
        }
    }

}