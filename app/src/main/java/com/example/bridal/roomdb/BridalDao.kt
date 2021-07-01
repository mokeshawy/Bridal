package com.example.bridal.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bridal.model.ProductModel

@Dao
interface BridalDao {

    @Insert
    suspend fun insertProduct(productModel: ProductModel)

    @Query("DELETE FROM ProductModel WHERE pushKey = :pushKey")
    suspend fun deleteProduct( pushKey : String)

    @Query("SELECT * FROM ProductModel WHERE pushKey = :pushKey")
    suspend fun selectByPushKey( pushKey: String) : List<ProductModel>

    @Query("SELECT * FROM ProductModel")
    suspend fun selectAll() : List<ProductModel>
}