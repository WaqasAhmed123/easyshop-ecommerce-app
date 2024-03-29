package com.example.easyshop.view.app

import PreferenceDataStoreService
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch

class AppViewModel(context: Context) : ViewModel() {
    var hasToken = mutableStateOf(false)
    var checkingToken = mutableStateOf(false)
    val preferenceDataStoreService=PreferenceDataStoreService(context=context)




    init {
        viewModelScope.launch {
            checkingToken.value=true
            hasToken.value = preferenceDataStoreService.hasToken(context)
            checkingToken.value=false

        }


    }

}