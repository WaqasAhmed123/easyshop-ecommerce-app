package com.example.easyshop.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.easyshop.R
import textStyle


@Composable
//fun CartProductBox(productName: String) {
fun CartProductBox(
    image:String,
    productName: String,
//    brand: String,
    price: String,
    quantity: Int,
    onAddProductClick: () -> Unit,
    onDeleteProductClick: () -> Unit,
    onProductDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(10.dp))
//            .border(
//                1.dp,
//                MaterialTheme.colorScheme.secondary,
//                shape = RoundedCornerShape(10.dp)
//            )
            .clip(RoundedCornerShape(10.dp))


    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // First Box containing the image
            Box() {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .height(99.dp)
                        .width(126.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            // Second Box containing the text
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$productName",
                            style = textStyle()["titleMedium"]!!,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            modifier = Modifier
                                .clickable(onClick = onProductDelete)
                                .size(28.dp),
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color(0xFFF65A5A)
                        )
                    }
//                    Text(
//                        text = "$brand",
//                        style = textStyle(textColor = Color(0xFF9B9999))["bodySmall"]!!,
//                        modifier = Modifier.padding(top = 8.dp)
//                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)

                    ) {
                        Text(
                            text = "$price",
                            style = textStyle(textColor = MaterialTheme.colorScheme.primary)["bodySmall"]!!,
                        )
                        Spacer(modifier = Modifier.weight(0.5f))
                        AddDeleteProduct(onAddDeleteProductClick = onDeleteProductClick)
                        Text(
                            text = "$quantity",
                            style = textStyle()["titleMedium"]!!,
                            modifier = Modifier.padding(horizontal = 8.dp)

                        )
                        AddDeleteProduct(isAdd = true, onAddDeleteProductClick = onAddProductClick)
                    }
                }
            }
        }
    }
}

@Composable
fun AddDeleteProduct(isAdd: Boolean = false, onAddDeleteProductClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
//                            .padding(2.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(28.dp)
            .clickable(onClick = onAddDeleteProductClick)

    ) {
        Icon(
            imageVector = if (isAdd) {

                Icons.Filled.Add

            } else {
                Icons.Filled.Remove

            }, contentDescription = null, tint = Color.White
        )
    }
}
