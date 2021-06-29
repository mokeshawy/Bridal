package com.example.bridal.roomdb

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bridal.util.Constants

object DatabaseModule {

    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.DATA_BASE_NAME
    ).build()
}