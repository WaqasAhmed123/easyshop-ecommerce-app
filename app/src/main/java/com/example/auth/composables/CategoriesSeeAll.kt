package com.example.auth.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import textStyle

@Composable
fun CategoriesSeeAll(category: String,onClick: () -> Unit,navController: NavController) {
    Row (verticalAlignment = Alignment.CenterVertically){
        Text(text = category, style = textStyle()["titleLarge"]!!)
        Spacer(modifier = Modifier.weight(1f))
//        TextButton(onClick = onClick) {
        TextButton(onClick = {navController.navigate("all_products_view")}) {
            Text(
                text = "See All",
                style = textStyle(textColor = MaterialTheme.colorScheme.primary)["bodySmall"]!!
            )

        }

    }
}