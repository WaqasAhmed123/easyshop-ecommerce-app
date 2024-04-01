package com.example.easyshop.view.app

import PreferenceDataStoreService
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyshop.room_db.RoomInstance
import kotlinx.coroutines.launch

class AppViewModel(context: Context) : ViewModel() {
    var hasToken = mutableStateOf(false)
    var checkingToken = mutableStateOf(false)
    val preferenceDataStoreService = PreferenceDataStoreService(context = context)



    init {
        viewModelScope.launch {
            checkingToken.value = true
            hasToken.value = preferenceDataStoreService.hasToken(context)
            checkingToken.value = false
            println("rrom in app ${RoomInstance.db?.dao}")
//            ProtoDataStoreService.saveCartItems(cartItems = dummyCartItems,context)
//            ProtoDataStoreService.getSavedCartItems(context)

        }


    }

}