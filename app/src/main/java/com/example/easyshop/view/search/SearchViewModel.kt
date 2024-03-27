package com.example.easyshop.view.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository.allProductsList


class SearchViewModel:ViewModel() {
    var searchProduct = mutableStateOf("")
    var visibleSearchResult = mutableStateOf(false)
//    var totalResults = mutableStateOf<Int>(0)

    fun filterProductsByKeyword(): List<ProductModel> {
        return if (searchProduct.value.isNotBlank()) {
            allProductsList.filter { product ->
                product.title.contains(
                    searchProduct.value, ignoreCase = true
                ) || product.description.contains(
                    searchProduct.value, ignoreCase = true
                )
            }
        } else {
           return emptyList()
        }
    }


}