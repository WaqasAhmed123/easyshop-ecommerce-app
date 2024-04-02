package com.example.easyshop.service.retrofit

import com.example.easyshop.repository.ProductsRepository
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val BASE_URL = "https://fakestoreapi.com"

    fun getPinnedCertificate(): CertificatePinner {
        val certificate=CertificatePinner.Builder()
            .add("example.com", "sha256/CKr590BCHX3qCLZKJy0djpxySepxSYY4du6K2WxEmrY=")
//            .add("fakestoreapi.com", "sha256/CKr590BCHX3qCLZKJy0djpxySepxSYY4du6K2WxEczv=")
            // Add more certificates or public keys here if needed
            .build()
        println("certificate is ${certificate.pins}")
        return certificate

    }

    private fun getPinnedOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().certificatePinner(getPinnedCertificate())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    //    val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .client(getPinnedOkHttpClient())
//        .build().create(ApiService::class.java)
//    val retrofitBuilder =
//        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).
//        client(getPinnedOkHttpClient())
//            .baseUrl(
//            BASE_URL
//        ).build().create(ApiService::class.java)

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(getPinnedOkHttpClient())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}