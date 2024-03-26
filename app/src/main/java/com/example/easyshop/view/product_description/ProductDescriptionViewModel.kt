package com.example.easyshop.view.product_description

import androidx.lifecycle.ViewModel
import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository.allProductsList
import com.example.easyshop.view.cart.CartViewModel

class ProductDescriptionViewModel(private val cartViewModel: CartViewModel) : ViewModel() {
    var product: ProductModel? = null


}