package com.example.easyshop.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.easyshop.model.ProductModel
import com.example.easyshop.service.ApiService
import com.example.easyshop.view.home.HomeViewModel
import com.google.firebase.auth.UserInfo
//import okhttp3.Callback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductsRepository {

    val BASE_URL = "https://fakestoreapi.com"
    var allProductsList = mutableStateListOf<ProductModel>()
    var selectedCategoryProducts = mutableStateListOf<ProductModel>()
    var allCategories = mutableStateListOf<String>()
    var isDataLoaded = mutableStateOf(false)

    val retrofitBuilder =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build().create(ApiService::class.java)

    fun getAllProductsFromApi() {
//        println("location while calling func $lat, $lon")
        println("fetching")
        val retrofitData = retrofitBuilder.getAllProducts()
        retrofitData.enqueue(object : Callback<List<ProductModel>> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<List<ProductModel>>, response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful) {
                    println("rep is $response")
                    val productsData = response.body()
                    println("resp of api boyd ${productsData}")
                    productsData?.let {
                        allProductsList.clear() // Clear existing data
                        allProductsList.addAll(it) // Add new data
                    }
//                    allProductsList=productsData
//                    HomeVie"wModel.updateWeatherDataInHomeViewModel(completeWeatherData!!)


//                    isDataLoaded.value = true

                } else {
                    println("Response not successful: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
            }
        })


    }

//   suspend fun getProductsByCategoryFromApi(categoryName:String) {
////        println("location while calling func $lat, $lon")
//        println("fetching")
//        val retrofitData = retrofitBuilder.getProductsByCategory(categoryName)
//        retrofitData.enqueue(object : Callback<List<ProductModel>> {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onResponse(
//                call: Call<List<ProductModel>>, response: Response<List<ProductModel>>
//            ) {
//                if (response.isSuccessful) {
//                    println("rep is $response")
//                    val categoriesData = response.body()
//                    println("products by category ${categoriesData}")
//                    categoriesData?.let {
//                        selectedCategoryProducts.clear() // Clear existing data
//                        selectedCategoryProducts.addAll(it) // Add new data
//                    }
//
//                } else {
//                    println("Response not successful: ${response.code()}")
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
//                Log.d("TAG", "onFailure: " + t.message)
//            }
//        })
//
//
//    }
suspend fun getProductsByCategoryFromApi(categoryName:String) {
    println("fetching")
//    val retrofitData = retrofitBuilder.getProductsByCategory(categoryName)
    try {
        val response = retrofitBuilder.getProductsByCategory(categoryName)
        if (response.isSuccessful) {
            val categoriesData = response.body()
            println("products by category ${categoriesData}")
            categoriesData?.let {
                selectedCategoryProducts.clear() // Clear existing data
                selectedCategoryProducts.addAll(it) // Add new data
            }
        } else {
            println("Response not successful: ${response.code()}")
        }
    } catch (e: Exception) {
        println("Exception: ${e.message}")
    }
}


    fun getAllCategoriesFromApi() {
//        println("location while calling func $lat, $lon")
        println("fetching")
        val retrofitData = retrofitBuilder.getAllCategories()
        retrofitData.enqueue(object : Callback<List<String>> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<List<String>>, response: Response<List<String>>
            ) {
                if (response.isSuccessful) {
                    println("rep is $response")
                    val allCategoriesData = response.body()
                    println("resp of categories ${allCategoriesData}")
//                    allCategories= allCategoriesData as SnapshotStateList<String>
                    allCategoriesData?.let {
                        allCategories.clear() // Clear existing data
                        allCategories.addAll(it) // Add new data
                    }
                    isDataLoaded.value = true

//                    productsData?.let {
//                        allProductsList.clear() // Clear existing data
//                        allProductsList.addAll(it) // Add new data
//                    }
//                    allProductsList=productsData
//                    HomeVie"wModel.updateWeatherDataInHomeViewModel(completeWeatherData!!)


//                    isDataLoaded.value = true

                } else {
                    println("Response not successful: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
            }
        })


    }
}