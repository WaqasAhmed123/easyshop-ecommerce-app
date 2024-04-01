package com.example.easyshop.room_db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CartItem::class],
    version = 1

)
abstract class CartDatabase:RoomDatabase() {
    abstract val  dao:CartDao
}