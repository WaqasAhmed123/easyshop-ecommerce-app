package com.example.auth.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.auth.R
import com.example.auth.composables.CategoriesSeeAll
import com.example.auth.composables.ItemTitleWithImage
import com.example.auth.composables.SubmitButton
import com.example.auth.service.FirebaseService
import kotlinx.coroutines.launch
import textStyle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavController) {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()
//    Scaffold(modifier = Modifier.padding(16.dp)) {
    Scaffold(
    ) {
        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

//            contentAlignment = Alignment.Center
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(

                        painter = painterResource(id = R.drawable.signup),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            //                        .size(size)
                            .clip(CircleShape)// Clip the image to a circular shape
                    )
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "Hello", style = textStyle(textColor = Color.Gray)["bodySmall"]!!
                        )
                        Text(
                            text = "${HomeViewModel.userName.value}",
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
                            .background(Color(0xFFF8F7F7)), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.NotificationsNone,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                    },
                    placeholder = {
                        Text(
                            text = "Search here",
                            style = textStyle(textColor = Color.Gray)["bodySmall"]!!
                        )
                    },


                    value = HomeViewModel.search.value,
                    textStyle = textStyle(textColor = Color.Gray)["bodySmall"]!!,
                    onValueChange = {

                        HomeViewModel.search.value = it
//                        println(inputText.value)
                    },
                    modifier = Modifier
                        .border(
                            BorderStroke(width = 2.dp, color = Color.Gray),
                            shape = RoundedCornerShape(50)
                        )
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = (MaterialTheme.colorScheme.secondary),
//                        containerColor = Color.Transparent,
//                        backgroundColor = Purple200.copy(alpha = 0.5f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))


                val pagerState = rememberPagerState(pageCount = {
                    4
                })
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
                Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Column {

                        CategoriesSeeAll(category = "Featured", onClick = {})
                        LazyRow {
                            itemsIndexed(HomeViewModel.itemsName) { index, dayData ->
                                val itemName = HomeViewModel.itemsName[index]
                                val itemPrice = HomeViewModel.itemsPrice[index]
//                        ItemTitleWithImage(onItemClick = {}, itemName = "Watch", itemPrice = "$40")
                                ItemTitleWithImage(
                                    onItemClick = {},
                                    onAddItemClick = {},
                                    itemName = itemName,
                                    itemPrice = itemPrice
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                // You can access both index and data here
                                // For example:
                                // WeatherItem(index = index, iconUrl = dayData[0] as String, temp = dayData[1] as String, day = dayData[2] as String)
                            }
                        }
                        CategoriesSeeAll(category = "Most Popular", onClick = {})
                        LazyRow {
                            itemsIndexed(HomeViewModel.itemsName) { index, dayData ->
                                val itemName = HomeViewModel.itemsName[index]
                                val itemPrice = HomeViewModel.itemsPrice[index]
//                        ItemTitleWithImage(onItemClick = {}, itemName = "Watch", itemPrice = "$40")
                                ItemTitleWithImage(
                                    onItemClick = {},
                                    onAddItemClick = {},
                                    itemName = itemName,
                                    itemPrice = itemPrice,
//                            isAddButton = true
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                // You can access both index and data here
                                // For example:
                                // WeatherItem(index = index, iconUrl = dayData[0] as String, temp = dayData[1] as String, day = dayData[2] as String)
                            }
                        }
                    }


                }


            }
        }
    }
}
//}