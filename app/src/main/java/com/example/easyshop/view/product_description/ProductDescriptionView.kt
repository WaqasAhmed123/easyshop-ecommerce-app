package com.example.easyshop.view.product_description

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.easyshop.composables.ProductSizeBox
import com.example.easyshop.composables.SubmitButton

import com.example.easyshop.view.cart.CartViewModel
import kotlinx.coroutines.launch
import textStyle


@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDescriptionView(
    productDescriptionViewModel: ProductDescriptionViewModel, cartViewModel: CartViewModel,
//    navController: NavController, productIndex: Int
//    navController: NavController, productId: Int

    navController: NavController
) {
//    val isAlreadyInCart by productDescriptionViewModel.isAlreadyInCart.collectAsState()
    var isFavourite = mutableStateOf(false)
    val snackbarHostState = remember { SnackbarHostState() }
//    var product = ProductsRepository.allProductsList[productIndex]
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        val scope = rememberCoroutineScope()
        val isAlreadyInCart by productDescriptionViewModel.isAlreadyInCart.collectAsState()

//        LaunchedEffect(key1 = Unit) {
//            productDescriptionViewModel.updateIsAlreadyInCart()
//            println("checking through launcheffect")
//
//        }
        Column {
            Box() {

                AsyncImage(
//                    model =  product.image,
                    model = productDescriptionViewModel.product?.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,

                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        //                        .fillMaxSize()
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 10.dp,
                                bottomStart = 10.dp,
                                topEnd = 0.dp,
                                topStart = 0.dp
                            )
                        ) // Clip the image to a circular shape
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
//                    text = productName,
                        text = "${productDescriptionViewModel.product?.title}", style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.W500, color = Color.Black
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
//                    text = productPrice,
                        text = "$${productDescriptionViewModel.product?.price}",
//                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W500, color = Color.Black)
                        style = textStyle(textColor = MaterialTheme.colorScheme.primary)["titleLarge"]!!
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${productDescriptionViewModel.product?.rating?.rate}",
                            style = textStyle()["bodySmall"]!!
                        )

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Description", style = textStyle()["titleLarge"]!!)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = productDescriptionViewModel.product?.description!!,
                        style = textStyle(textColor = Color(0xFF9B9999))["bodySmall"]!!
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Size", style = textStyle()["titleLarge"]!!)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        ProductSizeBox(8)
                        Spacer(modifier = Modifier.width(10.dp))
                        ProductSizeBox(10)
                        Spacer(modifier = Modifier.width(10.dp))
                        ProductSizeBox(38)
                        Spacer(modifier = Modifier.width(10.dp))
                        ProductSizeBox(40)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                    ) {
                        SubmitButton(
                            onClick = {
                                if (isAlreadyInCart) {
                                    scope.launch {
                                        productDescriptionViewModel.deleteProductInDb()

                                    }
//                                    productDescriptionViewModel.updateIsAlreadyInCart()
//                                    if (productDescriptionViewModel.isAlreadyInCart.value) {
//                                        productDescriptionViewModel.removeProductFromCart()
//                                        productDescriptionViewModel.updateIsAlreadyInCart()

                                } else {
                                    scope.launch {
                                        productDescriptionViewModel.addProductToDb()
                                        println("added in db")
                                    }
//                                    productDescriptionViewModel.addProductToCart()
//                                    productDescriptionViewModel.updateIsAlreadyInCart()

                                }

//                                scope.launch {
//                                }
//                                CartViewModel.cartProducts.add(mutableListOf(productDescriptionViewModel?.product, 0))

//                                productDescriptionViewModel.product?.let { product ->
////                                    cartViewModel.cartProducts.add(mutableListOf(product, 1))
//                                    cartViewModel.cartProducts.add(
//                                        CartItem(
//                                            product = product,
//                                            quantity = 1
//                                        )
//                                    )
//                                }

                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
//                                        message = "Product Added to Cart",
                                        message = productDescriptionViewModel.snackbarMessage,
                                        actionLabel = "View",
                                        duration = SnackbarDuration.Short
                                    )
                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {/* Handle snackbar action performed */
                                            navController.navigate("cart_view")
                                        }

                                        SnackbarResult.Dismissed -> {/* Handle snackbar dismissed */
                                        }
                                    }
                                }
//                            }, buttonTitle = "Add to Cart",
                            },
                            buttonWidth = 7f,
//                            buttonTitle = if (productDescriptionViewModel.isAlreadyInCart.value) {
                            buttonTitle = if (isAlreadyInCart) {
                                "Remove From Cart"

                            } else {

                                "Add to Cart"
                            }
                        )
//                        SubmitButton(
//                            onClick = { /*TODO*/ }, buttonTitle = "Remove From Cart", buttonWidth = 0.65f
////                            onClick = { /*TODO*/ }, buttonTitle = "Remove From Cart"
//                        )
                    }
                }

            }
        }

    }


}