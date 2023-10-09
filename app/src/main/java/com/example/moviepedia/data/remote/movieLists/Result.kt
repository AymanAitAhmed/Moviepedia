package com.example.moviepedia.data.remote.movieLists

data class Result(
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)