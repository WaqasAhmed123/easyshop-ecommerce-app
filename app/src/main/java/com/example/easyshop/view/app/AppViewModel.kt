package com.example.easyshop.view.app

import DataStoreService
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch

class AppViewModel(context: Context) : ViewModel() {
    var hasToken = mutableStateOf(false)
    var checkingToken = mutableStateOf(false)
    val dataStoreService=DataStoreService(context=context)




    init {
        viewModelScope.launch {
            checkingToken.value=true
            hasToken.value = dataStoreService.hasToken(context)
            checkingToken.value=false

        }


    }

}