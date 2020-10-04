package com.example.madlevel4task2

import android.content.Context

public class ResultRepository(context: Context) {

    private var resultDao: ResultDao

    init {
        val shoppingListRoomDatabase = ResultRoomDatabase.getDatabase(context)
        resultDao = shoppingListRoomDatabase!!.resultDao()
    }

    suspend fun getAllResults(): List<Result> {
        return resultDao.getAllResults()
    }

    suspend fun insertResult(result: Result) {
        resultDao.insertResult(result)
    }

    suspend fun deleteResult(result: Result) {
        resultDao.deleteResult(result)
    }

    suspend fun updateResult(result: Result) {
        resultDao.updateResult(result)
    }

    suspend fun deleteAllResults() {
        resultDao.deleteAllResults()
    }

}
