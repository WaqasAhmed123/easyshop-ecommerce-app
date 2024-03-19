package com.example.easyshop.view.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.composables.CartProductBox
import com.example.easyshop.model.ProductModel
import com.google.android.play.core.integrity.z
import textStyle

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartView(navController: NavController) {
//    var cartList by remember { mutableStateOf(CartViewModel.cartProducts.entries.toList()) }

    Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = {
//        println("list of map is ${CartViewModel.cartProducts.entries.toList()}")
//        println("Type of entry value: ${entry.value.javaClass}")

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Cart", style = textStyle()["titleLarge"]!!
                )
            },
        )

    }) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            items(CartViewModel.cartProducts.size) { index ->
                val item = CartViewModel.cartProducts[index]
                val product = item[0] as ProductModel
                val quantity = item[1] as Int

                // Access product attributes
                val productName = product.title
                val image = product.image
                val price = product.price

                CartProductBox(
                    image = image,
                    productName = productName,
                    quantity = quantity,
                    price = "$${price}",
                    onAddProductClick = { CartViewModel.incrementQuantity(index) },
                    onDeleteProductClick = { CartViewModel.decrementQuantity(index) },
                    onProductDelete = { CartViewModel.deleteProduct(index) }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }


//        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())) {
//            itemsIndexed(CartViewModel.cartProducts) {index, pairs ->
//                CartProductBox(
//                    productName = pairs.firstOrNull { it.first == "productName" }?.second as? String
//                        ?: "",
////                    brand = pairs.firstOrNull { it.first == "brand" }?.second as? String ?: "",
//                    quantity = pairs.firstOrNull { it.first == "quantity" }?.second as? Int ?: 0,
//                    price = pairs.firstOrNull { it.first == "price" }?.second as? String ?: "",
//                    onAddProductClick ={CartViewModel.incrementQuantity(productIndex = index)
//                                       },
//                    onDeleteProductClick ={CartViewModel.decrementQuantity(productIndex = index)},
//                    onProductDelete ={CartViewModel.deleteProduct(productIndex = index)
//                                     println("deleted")
//                                     },
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//        }
    }
}

