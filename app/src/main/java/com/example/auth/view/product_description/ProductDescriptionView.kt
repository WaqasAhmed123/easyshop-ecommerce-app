package com.example.auth.view.product_description

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.auth.R
import com.example.auth.composables.ProductSizeBox
import com.example.auth.composables.SubmitButton
import com.example.auth.view.cart.CartViewModel
import kotlinx.coroutines.launch
import textStyle
import java.time.Duration


@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDescriptionView(
    navController: NavController, productName: String, productPrice: String
) {
    var isFavourite = mutableStateOf(false)
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        val scope = rememberCoroutineScope()
        Column {
            Box() {

                Image(
                    painter = painterResource(id = R.drawable.watch),
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
            Box(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
//                    text = productName,
                        text = "$productName", style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.W500, color = Color.Black
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
//                    text = productPrice,
                        text = "$productPrice",
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
                        Text(text = "4.5", style = textStyle()["bodySmall"]!!)

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Description", style = textStyle()["titleLarge"]!!)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Culpa aliquam consequuntur veritatis at consequuntur praesentium beatae temporibus nobis. Velit dolorem facilis neque autem. Itaque voluptatem expedita qui eveniet id veritatis eaque. Blanditiis quia placeat nemo. Nobis laudantium nesciunt perspiciatis sit eligendi.",
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
                                CartViewModel.cartProducts.add(
                                    listOf(
                                        Pair("productName", productName),
                                        Pair("brand", "rolex"),
                                        Pair("quantity", 2),
                                        Pair("price", productPrice)
                                    )
                                )

                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Product Added to Cart",
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
                            }, buttonTitle = "Add to Cart", buttonWidth = 0.35f
                        )
                        SubmitButton(
                            onClick = { /*TODO*/ }, buttonTitle = "Buy Now", buttonWidth = 0.5f
                        )
                    }
                }

            }
        }

    }


}