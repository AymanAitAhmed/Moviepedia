package com.example.moviepedia.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


data class MovieEntity(
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
