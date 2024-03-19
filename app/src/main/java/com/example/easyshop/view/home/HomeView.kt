package com.example.easyshop.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.CategoriesSeeAll
import com.example.easyshop.composables.ItemTitleWithImage
import com.example.easyshop.repository.ProductsRepository
import kotlinx.coroutines.delay
import textStyle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavController) {

//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }
    val scope = rememberCoroutineScope()
//    Scaffold(modifier = Modifier.padding(16.dp)) {
    Scaffold {
        if (!ProductsRepository.isDataLoaded.value) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
//                            .size(50.dp)
//                            .background(brush = gradientBackground)

            }

        } else {
            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, end = 16.dp, start = 16.dp)

//            contentAlignment = Alignment.Center
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(

                            painter = painterResource(id = R.drawable.signup),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable(onClick = { navController.navigate("profile_view") })
                                .height(48.dp)
                                .width(48.dp)
                                //                        .size(size)
                                .clip(CircleShape)// Clip the image to a circular shape
                        )
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Text(
                                text = "Hello",
                                style = textStyle(textColor = Color.Gray)["bodySmall"]!!
                            )
                            Text(
//                            text = "${HomeViewModel.userName.value}",
                                text = "${HomeViewModel.userName}",
                                style = textStyle()["titleMedium"]!!
                            )

                        }
                        //                    Box(modifier = Modifier.fillMaxSize())
                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable(onClick = {})
                                .clip(CircleShape)
                                .background(Color(0xFFF8F7F7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NotificationsNone,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.height(20.dp))


                    val pagerState = rememberPagerState(pageCount = {
                        4
                    })
//                LaunchedEffect(Unit) {
//                    while (true) {
//                        delay(2000)
//                        println("current page ${pagerState.currentPage} page count ${pagerState.pageCount-1}")
//                        if (pagerState.currentPage == pagerState.pageCount-1) {
//                            pagerState.animateScrollToPage(0)
//                        } else {
//                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                        }
//                    }
//                }
                    HorizontalPager(
                        state = pagerState,
//                    modifier = Modifier.fillMaxSize()
                    ) { page ->
                        Box(
                            modifier = Modifier
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
                                        text = "Get Winter Discount",
                                        style = textStyle(textColor = Color.White)["titleMedium"]!!
                                    )
                                    Text(
                                        text = "20% Off", style = TextStyle(
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.W500
                                        )
                                    )
                                    Text(
                                        text = "For Children ",
                                        style = textStyle(textColor = Color.White)["titleMedium"]!!
                                    )
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
//                        .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)
                            )
                        }
                    }
//                Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Box() {
                        Column {
                            CategoriesSeeAll(category = "Featured", onClick = {}, navController)
//                            LazyRow {
//                                itemsIndexed(HomeViewModel.itemsName) { index, dayData ->
//                                    val itemName = HomeViewModel.itemsName[index]
//                                    val itemPrice = HomeViewModel.itemsPrice[index]
////                        ItemTitleWithImage(onItemClick = {}, itemName = "Watch", itemPrice = "$40")
//                                    ItemTitleWithImage(
//                                        onItemClick = { navController.navigate("product_description_view?productName=${itemName}&productPrice=${itemPrice}") },
//                                        onAddItemClick = {},
//                                        itemName = itemName,
//                                        itemPrice = itemPrice,
////                                        image = "null"
//                                    )
//                                    Spacer(modifier = Modifier.width(10.dp))
//                                    // You can access both index and data here
//                                    // For example:
//                                    // WeatherItem(index = index, iconUrl = dayData[0] as String, temp = dayData[1] as String, day = dayData[2] as String)
//                                }
//                            }
                            CategoriesSeeAll(category = "Most Popular",
                                navController = navController,
                                onClick = {})
//                            LazyColumn {
//                                itemsIndexed(HomeViewModel.allProducts) { index, itemName ->
//                                    val price = HomeViewModel.allProducts.getOrNull(index)?.price
//                                        ?: "" // Ensure index safety
//                                    val title = HomeViewModel.allProducts.getOrNull(index)?.title
//                                        ?: "" // Ensure index safety
//                                    val image = HomeViewModel.allProducts.getOrNull(index)?.image
//                                        ?: "" // Ensure index safety
//
//
//                                    ItemTitleWithImage(onItemClick = {
//                                        navController.navigate("product_description_view?productName=$title&productPrice=$price")
//                                    },
//                                        onAddItemClick = { /* Handle add item click */ },
//                                        itemName = title,
//                                        itemPrice = price.toString()
//                                    )
//
//                                    Spacer(modifier = Modifier.width(10.dp))
//                                }
//                            }
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), // Set the number of columns in the grid
//                 contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(HomeViewModel.allProducts.size) { index ->
                                    val product = HomeViewModel.allProducts[index]
                                    val price = "$${product.price}" ?: "" // Ensure null safety
                                    val title = product.title ?: "" // Ensure null safety
                                    val image = product.image ?: "" // Ensure null safety
                                    ItemTitleWithImage(onItemClick = {
                                        println("passed index $index")
//                                        navController.navigate("product_description_view?productName=$title&productPrice=$price")
                                        navController.navigate("product_description_view/$index")
                                    },
                                        onAddItemClick = { /* Handle add item click */ },
                                        itemName = title,
                                        itemPrice = price.toString(),
                                        image = image
                                    )

//                                    ItemTitleWithImage(
////                                        isAddButton = true,
//                                        onItemClick = { /*TODO*/ },
//                                        onAddItemClick = { /*TODO*/ },
//                                        itemName = HomeViewModel.itemsName[index],
//                                        itemPrice = HomeViewModel.itemsPrice[index]
//                                    )
//                    Text(text = items[index])
                                }
                            }
                        }


                    }


                }
            }

        }


    }
}
//}