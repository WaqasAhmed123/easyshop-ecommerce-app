package com.example.easyshop.view.cart

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.model.ProductModel
import com.example.easyshop.model.Rating
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.room_db.CartItemLocal
import com.example.easyshop.room_db.RoomInstance
import com.example.easyshop.service.ProtoDataStoreService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


data class CartItem(
    val product: ProductModel, // Assuming ProductModel is your product data class
//    var quantity: Int
    var quantity: MutableState<Int>
)

class CartViewModel(private val context: Context) : ViewModel() {
    val cartDao = RoomInstance.db?.dao
    var cartProducts = mutableStateListOf<CartItem>()
    val cartProductsStateFlow: StateFlow<List<CartItemLocal>>? =
        cartDao?.getCartProducts()?.conflate()
            ?.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

//    val cartProducsDb: StateFlow<List<CartItemLocal>> = cartDao.getCartProducts()

    suspend fun addProductToDb(cartItemLocal: CartItemLocal) {
        cartDao?.addProductToCart(cartItemLocal)
    }
//    var _cartProductsLocal = MutableStateFlow<List<CartItemLocal>>(emptyList())
//    val cartProductsLocal: StateFlow<List<CartItemLocal>> = _cartProductsLocal
//var cartProductsLocal: StateFlow<List<CartItemLocal>> = ProtoDataStoreService.getSavedCartItemLocals(context)
//    suspend fun addCartProduct(cartItem:List<CartItemLocal> ){
//        ProtoDataStoreService.saveCartItemLocals(cartItems = cartItem, context = context)
//    }

//val dummyCartItem=CartItemLocal(
//    product = ProductModel(
//        id = 1,
//        title = "Product 1",
//        price = 10.0,
//        description = "Description of Product 1",
//        category = "Category 1",
//        image = "image_url_1",
//        rating = Rating(rate = 4.5, count = 20)
//    ),
//    quantity = 2
//)
//    val dummyCartItems: List<CartItemLocal> = listOf(
//        CartItemLocal(
//            product = ProductModel(
//                id = 1,
//                title = "Product 1",
//                price = 10.0,
//                description = "Description of Product 1",
//                category = "Category 1",
//                image = "image_url_1",
//                rating = Rating(rate = 4.5, count = 20)
//            ),
//            quantity = 2
//        ),
//        CartItemLocal(
//            product = ProductModel(
//                id = 2,
//                title = "Product 2",
//                price = 15.0,
//                description = "Description of Product 2",
//                category = "Category 2",
//                image = "image_url_2",
//                rating = Rating(rate = 4.0, count = 15)
//            ),
//            quantity = 3
//        )
//        // Add more dummy cart items as needed
//    )

    fun incrementQuantity(index: Int) {
        val item = cartProducts[index]
        item.quantity.value++
        cartProducts[index] = item
    }

    fun decrementQuantity(index: Int) {
        val item = cartProducts[index]
        if (item.quantity.value > 1) {
            item.quantity.value--
            cartProducts[index] = item
        }
    }

    fun deleteProduct(index: Int) {
        cartProducts.removeAt(index)
    }

    init {
        viewModelScope.launch {
//            ProtoDataStoreService.saveCartItemLocals(cartItems= dummyCartItems, context)
//            _cartProductsLocal.value=ProtoDataStoreService.getSavedCartItemLocals(context).value
//            println("in viewModel saved cart ${cartProductsLocal.value}")
        }
    }

}

//class CartViewModel:ViewModel() {
//
//    val cartProducts = mutableStateListOf<List<Any>>()
//
//    fun incrementQuantity(index: Int) {
//        val updatedQuantity = (cartProducts[index][1] as Int) + 1
//        cartProducts[index] = cartProducts[index].toMutableList().apply {
//            set(1, updatedQuantity)
//        }
//    }
//
//    fun decrementQuantity(index: Int) {
//        val currentQuantity = (cartProducts[index][1] as Int)
//        if (currentQuantity != 1) {
//            val updatedQuantity = currentQuantity - 1
//            cartProducts[index] = cartProducts[index].toMutableList().apply {
//                set(1, updatedQuantity)
//            }
//        }
//    }
//
//    fun deleteProduct(index: Int) {
//        cartProducts.removeAt(index)
//    }
//}
