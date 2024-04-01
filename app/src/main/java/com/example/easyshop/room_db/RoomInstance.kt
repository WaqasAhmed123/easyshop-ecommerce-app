package com.example.easyshop.room_db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object RoomInstance {
    var db: CartDatabase? = null

    fun initialize(applicationContext: Context) {
        db = Room.databaseBuilder(
            applicationContext.applicationContext, // Use application context
            CartDatabase::class.java, "contacts.db"
        ).build()
    }

//    fun getDatabase(): CartDatabase {
//        if (db == null) {
//            throw IllegalStateException("Database has not been initialized. Call initialize() first.")
//        }
//        return db!!
//    }
}
