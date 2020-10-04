package com.example.madlevel4task2

import androidx.room.*

@Dao
interface ResultDao {

    @Query("SELECT * FROM result_table")
    suspend fun getAllResults(): List<Result>

    @Insert
    suspend fun insertResult(result: Result)

    @Delete
    suspend fun deleteResult(result: Result)

    @Update
    suspend fun updateResult(result: Result)

    @Query("DELETE FROM result_table")
    suspend fun deleteAllResults()
}
