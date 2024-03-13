package com.example.auth.view.product_description

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import textStyle


@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDescriptionView(
    navController: NavController, productName: String, productPrice: String
) {
    var isFavourite = mutableStateOf(false)
    Scaffold {
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
                //            IconButton(
                //                onClick = {
                //                    isFavourite.value = !isFavourite.value
                //                }, modifier = Modifier
                //                    .align(Alignment.TopEnd)
                //                    .padding(top = 2.dp, end = 2.dp)
                //            ) {
                //                Icon(
                //                    modifier = Modifier
                //                        .size(24.dp)
                ////                    .background(Color.White),
                //                        .background(Color.Transparent),
                //                    imageVector = Icons.Outlined.Favorite,
                //                    contentDescription = "Favorite",
                //                    tint = if (isFavourite.value) {
                ////                            Color.Red
                //                        MaterialTheme.colorScheme.primary
                //                    } else {
                //                        Color.White
                //                    }
                //                )
                //            }
                //            Box(
                //                modifier = Modifier
                //                    .size(48.dp)
                //                    .align(Alignment.TopEnd)
                //                    .padding(top = 2.dp, end = 2.dp)
                //                    .clickable(onClick = {
                //                        isFavourite.value = !isFavourite.value
                //                        println("clicked")
                //                    })
                //                    .clip(CircleShape)
                //                    .background(Color(0xFFF8F7F7)),
                //                contentAlignment = Alignment.Center
                //            ) {
                //                Icon(
                //                    modifier = Modifier
                //                        .size(24.dp)
                ////                    .background(Color.White),
                //                        .background(Color.Transparent),
                //                    imageVector = Icons.Outlined.Favorite,
                //                    contentDescription = "Favorite",
                //                    tint = if (isFavourite.value) {
                ////                            Color.Red
                //                        MaterialTheme.colorScheme.primary
                //                    } else {
                //                        Color.LightGray
                //                    }
                //                )
                //            }
                //            Box(
                //                modifier = Modifier
                //                    .size(48.dp)
                //                    .align(Alignment.TopStart)
                //                    .padding(horizontal =  16.dp, vertical = 10.dp)
                //                    .clickable(onClick = { navController.popBackStack() })
                //                    .clip(CircleShape)
                //                    .background(Color(0xFFF8F7F7)), contentAlignment = Alignment.Center
                //            ) {
                //                Icon(
                //                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                ////                    modifier = Modifier.,
                //
                //                    contentDescription = null, modifier = Modifier.size(24.dp)
                //                )
                //            }

            }
            Box(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
//                    text = productName,
                        text = "productName", style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.W500, color = Color.Black
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
//                    text = productPrice,
                        text = "productPrice",
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
                }

            }
        }

    }


}