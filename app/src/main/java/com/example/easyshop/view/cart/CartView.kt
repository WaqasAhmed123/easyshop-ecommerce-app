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
fun CartView(navController: NavController, cartViewModel: CartViewModel) {

    Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Cart", style = textStyle()["titleLarge"]!!
                )
            },
        )

    }) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            items(cartViewModel.cartProducts.size) { index ->
                val item = cartViewModel.cartProducts[index]
//                val product = item[0] as ProductModel
//                val quantity = item[1] as Int
                val product = item.product
                val quantity = item.quantity

                // Access product attributes
                val productName = product.title
                val image = product.image
                val price = product.price

                CartProductBox(image = image,
                    productName = productName,
                    quantity = quantity,
                    price = "$${price}",
                    onAddProductClick = { cartViewModel.incrementQuantity(index) },
                    onDeleteProductClick = { cartViewModel.decrementQuantity(index) },
                    onProductDelete = { cartViewModel.deleteProduct(index) })

                Spacer(modifier = Modifier.height(10.dp))
            }
        }


    }
}

