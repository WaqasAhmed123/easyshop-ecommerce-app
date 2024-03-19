package com.example.easyshop.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import textStyle

@Composable
fun ProductSizeBox(size:Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(44.dp)
            .width(44.dp)
//            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(width = 1.dp, color = Color(0xFFCFCDCD)),
                shape = RoundedCornerShape(10)
            )
    ) {
        Text(text = "$size", style = textStyle()["titleMedium"]!!)
        // Content inside the Box
    }
}