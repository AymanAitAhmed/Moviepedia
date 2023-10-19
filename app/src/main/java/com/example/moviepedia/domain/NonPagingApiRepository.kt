package com.example.moviepedia.domain

import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse

interface NonPagingApiRepository {

    suspend fun getMovieDetails(id : Int) : MovieDetailsResponse

}