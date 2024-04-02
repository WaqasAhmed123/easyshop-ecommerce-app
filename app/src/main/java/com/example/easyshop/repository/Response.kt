package com.example.easyshop.repository

sealed class Result<T>(var data: T? = null, val errorMessage: String? = null) {

    class Loading<T>() : Result<T>()
    class Success<T>(data: T? = null) : Result<T>(data = data)
    class Error<T>(errorMessage: String?) : Result<T>(errorMessage = errorMessage)

}