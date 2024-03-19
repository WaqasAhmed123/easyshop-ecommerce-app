package com.example.easyshop.service

import com.example.easyshop.model.LoginRequest
import com.example.easyshop.model.LoginResponse
import com.example.easyshop.model.ProductModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/products")
    fun getAllProducts(
    ): Call<List<ProductModel>>
//    @GET("/products/category/{categoryName}")
//    fun getProductsByCategory(
////        @Query("categoryName") categoryName: String,
//        @Path("categoryName") String categoryName,
//    ): Call<List<ProductModel>>
    @GET("/products/category/{categoryName}")
    suspend fun getProductsByCategory(
        @Path("categoryName") categoryName: String
    ): Response<List<ProductModel>>

    @GET("/products/categories")
    fun getAllCategories(
    ): Call<List<String>>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}