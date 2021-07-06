package com.example.bridal.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bridal.model.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BridalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productModel: ProductModel)

    @Query("DELETE FROM ProductModel WHERE pushKey = :pushKey")
    suspend fun deleteProduct( pushKey : String)

    @Query("SELECT * FROM ProductModel WHERE pushKey = :pushKey")
    suspend fun selectByPushKey( pushKey: String) : List<ProductModel>

    @Query("SELECT * FROM ProductModel")
    fun selectAll() : Flow<List<ProductModel>>

    @Query("SELECT * FROM ProductModel")
    suspend fun readAll() : List<ProductModel>

    @Query("SELECT * FROM ProductModel WHERE userId = :userId")
    fun selectUserId(userId : String)  : List<ProductModel>
}