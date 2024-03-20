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
    suspend fun getAllProducts(
//        isDesc:Boolean,
//        @Query("sort") preview: Boolean?
//        @Query("sort") sort: String? = if (isDesc) "desc" else null
        @Query("sort") sort: String? = null
    ): Response<List<ProductModel>>

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