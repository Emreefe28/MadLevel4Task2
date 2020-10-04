package com.example.madlevel4task2

import androidx.room.*
import java.util.*

@Entity(tableName = "result_table")
data class Result(

    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "choiceYou")
    var choiceYou: Int,

    @ColumnInfo(name = "choicePc")
    var choicePc: Int,

    @ColumnInfo(name = "date")
    var date: Date?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)
