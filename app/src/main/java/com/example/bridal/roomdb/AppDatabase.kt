package com.example.bridal.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bridal.model.ProductModel

@Database( entities = [ProductModel::class] , version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun bridalDao() : BridalDao
}