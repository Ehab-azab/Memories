package com.example.memories.database

import androidx.room.*
import com.google.gson.Gson

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)

    var id: Int? = null,
    @ColumnInfo
    var title: String? = null,
    @ColumnInfo
    var day: String? = null,
    @ColumnInfo
    var month: String? = null,
    @ColumnInfo
    var year: String? = null,
    @ColumnInfo
    var description: String? = null,
    @ColumnInfo
    var date: String? = null,
    /*@ColumnInfo(name = "long")
    var long: Float? = null,*/
    @TypeConverters(converter::class)
    @ColumnInfo
    var pathImage: MutableList<String>? = null,

    @ColumnInfo(name = "lat")
    var lat: Float? = null,
    @ColumnInfo
    var startPoint: Float? = null,
    @ColumnInfo
    var endPoint: Float? = null,
    @ColumnInfo(name = "longe")
    var longe: Float? = null

)