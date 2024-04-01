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
        println("cartdao is $cartDao")
        println("cartdaodirect is ${RoomInstance.db?.dao}")
        println("db instance is ${RoomInstance.db}")
//        RoomInstance.db!!.dao?.addProductToCart(cartItemLocal)
        cartDao?.addProductToCart(cartItemLocal)
    }

    suspend fun deleteProductInDb(productId:Int) {
        cartDao?.deleteProductInCart(productId)
    }

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
            println("flow in db is ${cartProductsStateFlow?.value}")
//            ProtoDataStoreService.saveCartItemLocals(cartItems= dummyCartItems, context)
//            _cartProductsLocal.value=ProtoDataStoreService.getSavedCartItemLocals(context).value
//            println("in viewModel saved cart ${cartProductsLocal.value}")
        }
    }

}

