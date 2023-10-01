package com.example.moviepedia.data.movieDetails

data class MovieDetails(
    val backdrop_path: String,
    val genres: List<Genre>,
    val homepage: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val release_date: String,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)