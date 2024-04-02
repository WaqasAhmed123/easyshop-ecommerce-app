package com.example.easyshop.repository

import PreferenceDataStoreService
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.example.easyshop.model.LoginRequest
import com.example.easyshop.model.ProductModel
import com.example.easyshop.service.retrofit.ApiService
import com.example.easyshop.service.retrofit.RetrofitInstance
import kotlinx.coroutines.coroutineScope
//import okhttp3.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductsRepository {
//    val userRepository=UserRepository()

    var allProductsList = mutableStateListOf<ProductModel>()
    var selectedCategoryProducts = mutableStateListOf<ProductModel>()
    var allCategories = mutableStateListOf<String>()
//    val RetrofitInstance.apiService =
//        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
//            .build().create(ApiService::class.java)

    suspend fun getAllProductsFromApi(isDesc: Boolean) {
        println("fetching")
        try {
            val response = RetrofitInstance.apiService.getAllProducts(if (isDesc) "desc" else null)
            if (response.isSuccessful) {
                if (response.isSuccessful) {
                    println("rep is $response")
                    val productsData = response.body()
                    println("resp of api boyd ${productsData}")
                    productsData?.let {
                        allProductsList.clear() // Clear existing data
                        allProductsList.addAll(it) // Add new data
                    }
                }
            } else {
                println("Response not successful: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
        }
    }

    suspend fun getProductsByCategoryFromApi(categoryName: String) {
        println("fetching")
//    val retrofitData = RetrofitInstance.apiService.getProductsByCategory(categoryName)
        try {
            val response = RetrofitInstance.apiService.getProductsByCategory(categoryName)
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

    suspend fun loginFromApi(
        loginCredentials: LoginRequest, navController: NavController, context: Context
    ): Boolean {
//        LoginViewModel.isLoggingIn.value = true
        println("loggingin")
//    val retrofitData = RetrofitInstance.apiService.getProductsByCategory(categoryName)
        var isLoginSuccessful = false
        try {
            val response = RetrofitInstance.apiService.login(loginCredentials)
            if (response.isSuccessful) {
                val token = response.body()?.token
                println("obtained token ${token}")
                val preferenceDataStoreService = PreferenceDataStoreService(context = context)
                coroutineScope {
                    preferenceDataStoreService.saveToken(token!!)
                    preferenceDataStoreService.saveUsername(loginCredentials.username)

                }

//                try {
//                    val createResponse = credentialManager?.createCredential(
//                        request = CreatePasswordRequest(
//                            loginCredentials.username,
//                            loginCredentials.password
//                        ), context = context
////                        activity = activity,
//                    )
//                } catch (e: CreateCredentialCancellationException) {
////                    _errorMessage.value = "User cancelled the save flow"
//                } catch (e: CreateCredentialException) {
////                    _errorMessage.value = "Credentials cannot be saved"
//                }
                navController.navigate("tab_view") {
                    popUpTo("login_view") {
                        inclusive = true
                    }
                }
                isLoginSuccessful = true
                return isLoginSuccessful
//                LoginViewModel.userName.value = ""
//                LoginViewModel.password.value = ""
//            categoriesData?.let {
//                selectedCategoryProducts.clear() // Clear existing data
//                selectedCategoryProducts.addAll(it) // Add new data
//            }
            } else {
                Toast.makeText(
                    context,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                println("Response not successful: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
        }
        return isLoginSuccessful
//        LoginViewModel.isLoggingIn.value = false
    }


    suspend fun getAllCategoriesFromApi() {
//        println("location while calling func $lat, $lon")
        println("fetching")
        try {
            val response = RetrofitInstance.apiService.getAllCategories()
            if (response.isSuccessful) {
                println("rep is $response")
                val allCategoriesData = response.body()
                println("resp of categories ${allCategoriesData}")
//                    allCategories= allCategoriesData as SnapshotStateList<String>
                allCategoriesData?.let {
                    allCategories.clear() // Clear existing data
                    allCategories.addAll(it) // Add new data// Add new data
                }
            } else {
                println("Response not successful: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
        }
    }
//    fun getAllCategoriesFromApi() {
////        println("location while calling func $lat, $lon")
//        println("fetching")
//        val retrofitData = RetrofitInstance.apiService.getAllCategories()
//        retrofitData.enqueue(object : Callback<List<String>> {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onResponse(
//                call: Call<List<String>>, response: Response<List<String>>
//            ) {
//                if (response.isSuccessful) {
//                    println("rep is $response")
//                    val allCategoriesData = response.body()
//                    println("resp of categories ${allCategoriesData}")
////                    allCategories= allCategoriesData as SnapshotStateList<String>
//                    allCategoriesData?.let {
//                        allCategories.clear() // Clear existing data
//                        allCategories.addAll(it) // Add new data
//                    }
////                    isDataLoaded.value = true
//
////                    productsData?.let {
////                        allProductsList.clear() // Clear existing data
////                        allProductsList.addAll(it) // Add new data
////                    }
////                    allProductsList=productsData
////                    HomeVie"wModel.updateWeatherDataInHomeViewModel(completeWeatherData!!)
//
//
////                    isDataLoaded.value = true
//
//                } else {
//                    println("Response not successful: ${response.code()}")
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<String>>, t: Throwable) {
//                Log.d("TAG", "onFailure: " + t.message)
//            }
//        })
//
//
//    }
}