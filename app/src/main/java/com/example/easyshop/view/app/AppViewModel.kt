package com.example.easyshop.view.app

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch

class AppViewModel(context: Context) : ViewModel() {
    var hasToken = mutableStateOf(false)
    var checkingToken = mutableStateOf(false)



    init {
        viewModelScope.launch {
            checkingToken.value=true
            hasToken.value = SharedPreferenceService.hasToken(context = context)
            checkingToken.value=false

        }


    }

}