package com.example.easyshop.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easyshop.R
import textStyle

@Composable
fun CategoriesBox (category:String,onCategoriesClick: () -> Unit){
    Box(
        modifier = Modifier
            .clickable(onClick = onCategoriesClick)
            .height(135.dp)
            .width(343.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp) // Adjust the corner radius as needed
            )

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "Get Discount on $category",
                    style = textStyle(textColor = Color.White)["titleMedium"]!!
                )
                Text(
                    text = "20% Off", style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W500
                    )
                )
//                Text(
//                    text = "For Children ",
//                    style = textStyle(textColor = Color.White)["titleMedium"]!!
//                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.child_home),
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .width(89.dp)

            )

        }
    }
}