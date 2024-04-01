package com.example.easyshop.view.product_description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.model.ProductModel
import com.example.easyshop.room_db.CartItem
import com.example.easyshop.view.cart.CartViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDescriptionViewModel(private val cartViewModel: CartViewModel) : ViewModel() {
    var product: ProductModel? = null
    var snackbarMessage: String = ""
    val isAlreadyInCart: StateFlow<Boolean> =
        cartViewModel.cartProducts!!.map { cartProducts ->
            cartProducts.any { cartItemLocal ->
                cartItemLocal.product.id == product!!.id
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)


    suspend fun deleteProductInDb() {
        viewModelScope.launch {
            cartViewModel.deleteProductInDb(
                productId = product!!.id
            )
            snackbarMessage = "Product Removed From Cart"
        }
    }

    suspend fun addProductToDb() {
        viewModelScope.launch {
            cartViewModel.addProductToDb(
                cartItemLocal = CartItem(
                    product = product!!, quantity = 1, productId = product?.id!!
                )
            )
            snackbarMessage = "Product Added to Cart"
        }
    }

}
