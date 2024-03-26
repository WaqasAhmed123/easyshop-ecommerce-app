package com.example.easyshop.view.product_description

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository.allProductsList
import com.example.easyshop.view.cart.CartItem
import com.example.easyshop.view.cart.CartViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDescriptionViewModel(private val cartViewModel: CartViewModel) : ViewModel() {
    var product: ProductModel? = null
    var snackbarMessage:String=""

    //    private val _isAlreadyInCart = MutableStateFlow<Boolean>(cartViewModel.cartProducts.any { cartItem -> cartItem.product.id == product?.id })
//    val isAlreadyInCart: StateFlow<Boolean> = _isAlreadyInCart.asStateFlow()
    var isAlreadyInCart =
//        mutableStateOf(cartViewModel.cartProducts.any { cartItem -> cartItem.product.id == product?.id })
        mutableStateOf(cartViewModel.cartProducts.any { cartItem -> cartItem.product == product })
//        private set

    //    init {
//        updateIsAlreadyInCart()
//    }
//
    fun updateIsAlreadyInCart() {
        isAlreadyInCart.value =
            cartViewModel.cartProducts.any { cartItem -> cartItem.product.id == product?.id }

    }

    fun addProductToCart() {
        product?.let { product ->
            cartViewModel.cartProducts.add(
                CartItem(
                    product = product, quantity = 1
                )
            )
        }
        snackbarMessage="Product Added to Cart"
    }

    fun removeProductFromCart() {
        product?.let { product ->
            cartViewModel.cartProducts.remove(
                CartItem(
                    product = product, quantity = 1
                )
            )
        }
        snackbarMessage="Product Removed From Cart"

    }
}
