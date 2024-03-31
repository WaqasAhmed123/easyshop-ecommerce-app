package com.example.easyshop.service

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.easyshop.proto.UserCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object UserCartSerializer : Serializer<UserCart> {
    override val defaultValue: UserCart = UserCart.getDefaultInstance()

    //        get() = TODO("Not yet implemented")
    override suspend fun readFrom(input: InputStream): UserCart = withContext(Dispatchers.IO) {
        try {
            return@withContext UserCart.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

//    override suspend fun readFrom(input: InputStream): UserCart {
//        TODO("Not yet implemented")
//    }

//    override suspend fun writeTo(t: UserCart, output: OutputStream) {
//        TODO("Not yet implemented")
//    }
override suspend fun writeTo(t: UserCart, output: OutputStream) = t.writeTo(output)

    val Context.userInfoDataStore: DataStore<UserCart> by dataStore(
        fileName = "UserCart.pb",
        serializer = UserCartSerializer
    )



}