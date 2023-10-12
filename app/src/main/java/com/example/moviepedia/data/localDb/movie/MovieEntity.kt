package com.example.moviepedia.data.localDb.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviepedia.data.localDb.RoomConverters

@Entity
@TypeConverters(RoomConverters::class)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val DbId : Int? = null,
    val id: Int,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
