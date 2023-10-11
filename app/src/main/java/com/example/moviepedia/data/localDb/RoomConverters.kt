package com.example.moviepedia.data.localDb

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class RoomConverters {

    private val moshi: Moshi=Moshi.Builder().build()
    @TypeConverter
    fun fromListToJson(list : List<Int>):String{
        return moshi.adapter<List<Int>>(Types.newParameterizedType(List::class.java,Int::class.javaObjectType)).toJson(list)
    }

    @TypeConverter
    fun fromJsonToList(json : String):List<Int>{
        return moshi.adapter<List<Int>>(Types.newParameterizedType(List::class.java,Int::class.javaObjectType)).fromJson(json) ?: listOf(0)
    }

}