package com.example.easyshop.view.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.CartProductBox
import com.example.easyshop.model.ProductModel
import com.example.easyshop.room_db.CartItem
import com.google.android.play.core.integrity.z
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import textStyle
import java.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartView(navController: NavController, cartViewModel: CartViewModel) {
    val scope = rememberCoroutineScope()

    val localCartProducts by cartViewModel.cartProducts!!.collectAsState(initial = null)
    Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = {


        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Cart", style = textStyle()["titleLarge"]!!
                )
            },
        )

    }) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            when {
                localCartProducts == null -> {
//                cartViewModel.isDataLoading.value -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

                    }

                }

                localCartProducts!!.isEmpty() -> {
//                    LaunchedEffect(key1 = Unit){
//                        delay(2000)
//
//
//                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.empty_cart),
                                contentDescription = null,
                                modifier = Modifier
                                    //                        .height(198.dp) // Set height
                                    //                        .fillMaxHeight(0.4f) // Set height
                                    .fillMaxHeight(0.4f) // Set height
                                    //                        .width(336.dp)
                                    .fillMaxWidth()
//                            .align(Alignment.CenterHorizontally)

                            )
                            Text(
                                text = "Your cart is empty, explore to add.",
                                style = textStyle()["titleLarge"]!!,
                                textAlign = TextAlign.Center
                            )


                        }
                    }


                }

                else -> {
                    LazyColumn {
                        items(localCartProducts!!) { item ->
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
//                            onAddProductClick = { cartViewModel.incrementQuantity(index) },
                                onAddProductClick = { cartViewModel.incrementQuantity(productId = product.id) },
                                onDeleteProductClick = { cartViewModel.decrementQuantity(productId = product.id) },
//                            onDeleteProductClick = { cartViewModel.decrementQuantity(index) },
                                onProductDelete = {
                                    scope.launch {
                                        cartViewModel.deleteProductInDb(productId = product.id)

                                    }
                                })
                        }
                    }


                }
            }
//            if (localCartProducts.isNotEmpty()) {

//                LazyColumn {
//                    items(localCartProducts) { item ->
//                        val product = item.product
//                        val quantity = item.quantity
//
//                        // Access product attributes
//                        val productName = product.title
//                        val image = product.image
//                        val price = product.price
//                        CartProductBox(image = image,
//                            productName = productName,
//                            quantity = quantity,
//                            price = "$${price}",
////                            onAddProductClick = { cartViewModel.incrementQuantity(index) },
//                            onAddProductClick = { cartViewModel.incrementQuantity(productId = product.id) },
//                            onDeleteProductClick = { cartViewModel.decrementQuantity(productId = product.id) },
////                            onDeleteProductClick = { cartViewModel.decrementQuantity(index) },
//                            onProductDelete = {
//                                scope.launch {
//                                    cartViewModel.deleteProductInDb(productId = product.id)
//
//                                }
//                                cartViewModel.deleteProduct(index)


//                                    items (localCartProducts) { index ->
//                                val item = localCartProducts[index]
//                val product = item[0] as ProductModel
//                val quantity = item[1] as Int
//                        val product = item.value.product
//                        val quantity = item.value.quantity
//                                val product = item.product
//                                val quantity = item.quantity
//
//                                // Access product attributes
//                                val productName = product.title
//                                val image = product.image
//                                val price = product.price
//                                CartProductBox(image = image,
//                                    productName = productName,
//                                    quantity = quantity,
//                                    price = "$${price}",
//                                    onAddProductClick = { cartViewModel.incrementQuantity(index) },
//                                    onDeleteProductClick = { cartViewModel.decrementQuantity(index) },
//                                    onProductDelete = {
//                                        scope.launch {
//                                            cartViewModel.deleteProductInDb(productId = product.id)
//
//                                        }
//                                        cartViewModel.deleteProduct(index)
//                                    }
//                                )

//                                Spacer(modifier = Modifier.height(10.dp))
//                            })
//                    }
//                }
//            }


//            if (cartViewModel.cartProducts.isNotEmpty()) {
//
//                LazyColumn {
//                    items(cartViewModel.cartProducts.size) { index ->
//                        val item = cartViewModel.cartProducts[index]
////                val product = item[0] as ProductModel
////                val quantity = item[1] as Int
////                        val product = item.value.product
////                        val quantity = item.value.quantity
//                        val product = item.product
//                        val quantity = item.quantity
//
//                        // Access product attributes
//                        val productName = product.title
//                        val image = product.image
//                        val price = product.price
//                        CartProductBox(image = image,
//                            productName = productName,
//                            quantity = quantity.value,
//                            price = "$${price}",
//                            onAddProductClick = {
//
//                                cartViewModel.incrementQuantity(index)
//                            },
//                            onDeleteProductClick = { cartViewModel.decrementQuantity(index) },
//                            onProductDelete = { cartViewModel.deleteProduct(index) })
//
//                        Spacer(modifier = Modifier.height(10.dp))
//                    }
//                }


//            }
//            else {
//
//
//            }
        }

    }
}

