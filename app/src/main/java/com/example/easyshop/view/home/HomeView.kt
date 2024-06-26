package com.example.easyshop.view.home

//import com.example.easyshop.view.product_description.ProductDescriptionViewModel.product
import android.annotation.SuppressLint
import android.os.Build
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easyshop.R
import com.example.easyshop.composables.CategoriesBox
import com.example.easyshop.composables.ItemTitleWithImage
import com.example.easyshop.repository.ProductsRepository
import com.example.easyshop.repository.ProductsRepository.allProductsList
import com.example.easyshop.repository.ProductsRepository.getProductsByCategoryFromApi
import com.example.easyshop.repository.Result
import com.example.easyshop.repository.UserRepository
import com.example.easyshop.service.PermissionsService
import com.example.easyshop.view.product_description.ProductDescriptionViewModel
import kotlinx.coroutines.launch
import textStyle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(
    navController: NavController,
    homeViewModel: HomeViewModel,
    productDescriptionViewModel: ProductDescriptionViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val username by homeViewModel.userName.collectAsState()
    val allProducts by homeViewModel.allProducts.collectAsState(initial = Result.Loading())
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        PermissionsService.RequestNotificationPermissionDialog()
    }

    LaunchedEffect(key1 = Unit, block = {
        UserRepository.loadUsername(context = context, coroutineScope = scope)
//        ProductsRepository.getAllProductsFromApi(isDesc = homeViewModel.isDescProducts.value)
    })

    Scaffold {
        if (!homeViewModel.isDataLoaded.value) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

            }

        } else {
            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, end = 16.dp, start = 16.dp)


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
//                            text = "${homeViewModel.userName.value}",
                                text = username,
//                                text = "${user}",
                                style = textStyle()["titleMedium"]!!
                            )

                        }
                        //                    Box(modifier = Modifier.fillMaxSize())
                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable(onClick = {
//                                    scope.launch {
//                                        PermissionsService.RequestNotificationPermissionDialog(
//                                            context = context
//                                        )
//                                    }

                                })
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
//                    LaunchedEffect(Unit) {
//                        while (true) {
//                            delay(2000)
//                            println("current page ${pagerState.currentPage} page count ${pagerState.pageCount - 1}")
//                            if (pagerState.currentPage == pagerState.pageCount - 1) {
//                                pagerState.animateScrollToPage(0)
//                            } else {
//                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                            }
//                        }
//                    }
                    HorizontalPager(
                        state = pagerState,
//                    modifier = Modifier.fillMaxSize()
                    ) { page ->
                        val category = homeViewModel.allCategoriesList[page]
                        CategoriesBox(category = category, index = page, onCategoriesClick = {
                            scope.launch {
                                // Call the function and wait for its completion
                                getProductsByCategoryFromApi(category)

                                // Navigate after the function completes
                                navController.navigate("selected_products_view")
                                println("data after navigation ${ProductsRepository.selectedCategoryProducts.size}")
                            }
//                            navController.navigate("selected_products_view")

                        })
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "All Products",
                            style = textStyle()["titleLarge"]!!
                        )

                        IconButton(
                            onClick = {
                                homeViewModel.isDescProducts.value =
                                    !homeViewModel.isDescProducts.value
                                scope.launch {
                                    println("state is before${allProducts.data}")
                                    ProductsRepository.getAllProductsFromApi(isDesc = homeViewModel.isDescProducts.value)
                                    println("state is after${allProducts.data}")
                                }
//                                        LaunchedEffect(key1 = Unit, block = {
//
//                                        }
//
//                                        )
                            }, modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.Sort,
                                contentDescription = "Sort",
                                tint = Color.Black
                            )
                        }
                        Text(
                            text = "Sort", style = textStyle()["titleLarge"]!!
//                                    style = MaterialTheme.typography.body1,
//                                    modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box() {
                        when (allProducts) {
                            is Result.Error -> {
                                val errorMessage = allProducts.errorMessage
                                Text(text = errorMessage!!)
                            }
                            is Result.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

                                }
                            }

                            is Result.Success -> {
                                Column {
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Text(
//                                            modifier = Modifier.weight(1f),
//                                            text = "All Products",
//                                            style = textStyle()["titleLarge"]!!
//                                        )
//
//                                        IconButton(
//                                            onClick = {
//                                                homeViewModel.isDescProducts.value =
//                                                    !homeViewModel.isDescProducts.value
//                                                scope.launch {
//
//                                                    ProductsRepository.getAllProductsFromApi(isDesc = homeViewModel.isDescProducts.value)
//                                                }
////                                        LaunchedEffect(key1 = Unit, block = {
////
////                                        }
////
////                                        )
//                                            }, modifier = Modifier.size(24.dp)
//                                        ) {
//                                            Icon(
//                                                imageVector = Icons.AutoMirrored.Rounded.Sort,
//                                                contentDescription = "Sort",
//                                                tint = Color.Black
//                                            )
//                                        }
//                                        Text(
//                                            text = "Sort", style = textStyle()["titleLarge"]!!
////                                    style = MaterialTheme.typography.body1,
////                                    modifier = Modifier.padding(start = 4.dp)
//                                        )
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(2), // Set the number of columns in the grid
                                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        items(allProducts.data!!) { product ->
//                                            val product = allProducts.data!![index]
//                                        items(ProductsRepository.allProductsList.size) { index ->
//                                        val product = ProductsRepository.allProductsList[index]
                                            val price =
                                                "$${product.price}" ?: "" // Ensure null safety
                                            val title = product.title ?: "" // Ensure null safety
                                            val image = product.image ?: "" // Ensure null safety
                                            ItemTitleWithImage(
                                                productId = product.id,
                                                productDescriptionViewModel = productDescriptionViewModel,
//                                            onItemClick = {
//                                                println("passed index $index")
////                                        navController.navigate("product_description_view?productName=$title&productPrice=$price")
////                                        navController.navigate("product_description_view/$index")
//                                                productDescriptionViewModel.product =
//                                                    CommonFunctions.findProductById(product.id)
//                                                navController.navigate("product_description_view")
//                                            },
                                                onAddItemClick = { /* Handle add item click */ },
                                                itemName = title,
                                                itemPrice = price.toString(),
                                                image = image,
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            }

                        }
//                        if (homeViewModel.isDataLoaded.value) {

//                        } 
//                        else {
//                        Box(
//                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//                        ) {
//                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
//
//                        }

//                    }


                    }


                }
            }

//        }

        }
    }
}
//}