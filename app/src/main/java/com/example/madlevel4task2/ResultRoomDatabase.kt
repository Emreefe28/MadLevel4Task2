package com.example.madlevel4task2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ResultRoomDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private const val DATABASE_NAME = "RESULT_DATABASE"

        @Volatile
        private var resultRoomDatabaseInstance: ResultRoomDatabase? = null

        fun getDatabase(context: Context): ResultRoomDatabase? {
            if (resultRoomDatabaseInstance == null) {
                synchronized(ResultRoomDatabase::class.java) {
                    if (resultRoomDatabaseInstance == null) {
                        resultRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ResultRoomDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return resultRoomDatabaseInstance
        }
    }

}
