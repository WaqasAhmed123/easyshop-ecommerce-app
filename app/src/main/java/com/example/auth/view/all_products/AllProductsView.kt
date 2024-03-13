package com.example.auth.view.all_products

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import textStyle

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllProductsView(navController: NavController) {
    Scaffold(
        modifier = Modifier.padding(horizontal =  16.dp),
        topBar = {
            CenterAlignedTopAppBar(title = {   Text(
                text = "Products", style = textStyle()["titleLarge"]!!
            )},
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable(onClick = {})
                            .clip(CircleShape)
                            .background(Color(0xFFF8F7F7)), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,

                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Localized description"
//                        )
//                    }
                },
                )

//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .clickable(onClick = {})
//                        .clip(CircleShape)
//                        .background(Color(0xFFF8F7F7)), contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//                Text(
//                    text = "Products", style = textStyle()["titleLarge"]!!
//
////                        .padding(start = 8.dp)
////                        .weight(1f)
//                )
//            }

        }
    ) {

    }
}