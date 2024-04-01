package com.example.easyshop.room_db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object RoomInstance {
    var db: CartDatabase? = null

    fun initialize(context: Context) {
        db = Room.databaseBuilder(
            context.applicationContext, // Use application context
            CartDatabase::class.java, "cart.db"
        ).build()
    }

//    fun getDatabase(): CartDatabase {
//        if (db == null) {
//            throw IllegalStateException("Database has not been initialized. Call initialize() first.")
//        }
//        return db!!
//    }
}
