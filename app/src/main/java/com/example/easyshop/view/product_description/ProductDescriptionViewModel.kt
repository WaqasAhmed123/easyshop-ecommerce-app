package com.example.easyshop.view.product_description

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.model.ProductModel
import com.example.easyshop.repository.ProductsRepository.allProductsList
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.room_db.CartItemLocal
import com.example.easyshop.view.cart.CartItem
import com.example.easyshop.view.cart.CartViewModel
import com.google.android.play.integrity.internal.i
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDescriptionViewModel(private val cartViewModel: CartViewModel) : ViewModel() {
    var product: ProductModel? = null
    var snackbarMessage: String = ""

    //    private val _isAlreadyInCart = MutableStateFlow<Boolean>(cartViewModel.cartProducts.any { cartItem -> cartItem.product.id == product?.id })
//    val isAlreadyInCart: StateFlow<Boolean> = _isAlreadyInCart.asStateFlow()
    val isAlreadyInCart: StateFlow<Boolean> = cartViewModel.cartProductsStateFlow!!.map { cartProducts ->
        cartProducts.any { cartItemLocal ->
            cartItemLocal.product.id == product!!.id
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

//    var isAlreadyInCart =
//        mutableStateOf(cartViewModel.cartProducts.any { cartItem -> cartItem.product == product })
//        mutableStateOf(cartViewModel.cartProductsLocal.value.any { cartItem -> cartItem.product == product })
//        private set

    //    init {
//        updateIsAlreadyInCart()
//    }
//
//    fun updateIsAlreadyInCart() {
//        isAlreadyInCart.value =
//            cartViewModel.cartProductsLocal.value.any { cartItem -> cartItem.product.id == product?.id }
//        isAlreadyInCart.value =
//            cartViewModel.cartProducts.any { cartItem -> cartItem.product.id == product?.id }
//
//    }

    //    fun addProductToCart() {
//        product?.let { product ->
//            cartViewModel.addCartProduct(List<CartItemLocal>(CartItemLocal(product=product,1)))
//            List<CartItemL>(
//            cartViewModel.cartProducts.add(
//                CartItemLocal(
//                    product = product, quantity = mutableStateOf(1)
//                )
//            ))
//        }
//        snackbarMessage = "Product Added to Cart"
//    }
    fun addProductToCart() {
        product?.let { product ->
            cartViewModel.cartProducts.add(
                CartItem(
                    product = product, quantity = mutableStateOf(1)
                )
            )
        }
        snackbarMessage = "Product Added to Cart"
    }

    suspend fun deleteProductInDb() {
        viewModelScope.launch {
            cartViewModel.deleteProductInDb(
                productId = product!!.id
            )

        }
    }

    suspend fun addProductToDb() {
        viewModelScope.launch {
            cartViewModel.addProductToDb(
                cartItemLocal = CartItemLocal(
                    product = product!!, quantity = 1, productId = product?.id!!
                )
            )

        }
        product?.let { product ->
            cartViewModel.cartProducts.add(
                CartItem(
                    product = product, quantity = mutableStateOf(1)
                )
            )
        }
        snackbarMessage = "Product Added to Cart"
    }

    fun removeProductFromCart() {
//        cartViewModel.cartProducts.firstOrNull { cartItem ->
        cartViewModel.cartProducts.removeIf { cartItem ->
            cartItem.product == product
//            }
        }




        product?.let { product ->
            cartViewModel.cartProducts.remove(
                CartItem(
                    product = product, quantity = mutableStateOf(1)
                )
            )
        }
        snackbarMessage = "Product Removed From Cart"

    }
}
