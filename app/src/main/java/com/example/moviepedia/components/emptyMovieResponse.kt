package com.example.moviepedia.components

import com.example.moviepedia.data.remote.movieDetails.Genre
import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse
import com.example.moviepedia.data.remote.movieDetails.ProductionCompany
import com.example.moviepedia.data.remote.movieDetails.SpokenLanguage


val emptyMovieDetailsResponse = MovieDetailsResponse(
    genres = listOf(Genre(0)),
    homepage = "",
    original_title = "",
    overview = "",
    popularity = 0.0,
    poster_path = "",
    production_companies = listOf(ProductionCompany(0,"","","")),
    release_date = "",
    runtime = 0,
    spoken_languages = listOf(SpokenLanguage("")),
    status = "",
    title = "",
    vote_count = 0,
    vote_average = 0.0
)