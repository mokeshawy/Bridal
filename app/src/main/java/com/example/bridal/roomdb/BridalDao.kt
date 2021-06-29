package com.example.bridal.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bridal.model.ProductModel

@Dao
interface BridalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productModel: ProductModel)

    @Query("DELETE FROM ProductModel WHERE userId = :userId")
    suspend fun deleteProduct( userId : String)

    @Query("SELECT * FROM ProductModel")
    suspend fun selectAll() : List<ProductModel>
}