package com.example.easyshop.view.all_products

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.easyshop.composables.ItemTitleWithImage
import com.example.easyshop.view.home.HomeViewModel
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
                            .clickable(onClick = {navController.popBackStack()})
                            .clip(CircleShape)
                            .background(Color(0xFFF8F7F7)), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,

                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                )

        }
    ) {
        Box(modifier = Modifier.padding(top = it.calculateTopPadding())) {
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2), // Set the number of columns in the grid
////                 contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//                horizontalArrangement = Arrangement.spacedBy(20.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                items(HomeViewModel.itemsName.size) { index ->
//                    // Each item in the grid
//                    ItemTitleWithImage(
//                        isAddButton = true,
//                        onItemClick = { /*TODO*/ },
//                        onAddItemClick = { /*TODO*/ },
//                        itemName = HomeViewModel.itemsName[index],
//                        itemPrice = HomeViewModel.itemsPrice[index]
//                    )
////                    Text(text = items[index])
//                }
//            }
        }

    }
}