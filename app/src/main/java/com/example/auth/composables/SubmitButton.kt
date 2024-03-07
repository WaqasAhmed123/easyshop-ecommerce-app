package com.example.auth.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun SubmitButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.5f) // Set width to half of the screen width
//            .width(200.dp)
//            .widthIn(max = 200.dp)
            , // Limit maximum width
        shape = RoundedCornerShape(8.dp), // Rounded corners with a radius of 8.dp
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue), // Button color
        contentPadding = PaddingValues(16.dp) // Padding
    ) {
        Text(
            text = "Submit",
             style = MaterialTheme.typography.bodyMedium
             // Text color
        )
    }
    
}