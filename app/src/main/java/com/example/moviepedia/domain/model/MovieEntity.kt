package com.example.moviepedia.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


data class MovieEntity(
    val DbId : Int? = null,
    val id: Int? = null,
    val genre_ids: List<Int>?= null,
    val original_language: String?= null,
    val original_title: String?= null,
    val poster_path: String?= null,
    val release_date: String?= null,
    val title: String?= null,
    val vote_average: Double?= null,
    val vote_count: Int?= null
)
