package com.example.moviepedia.data.remote

import com.example.moviepedia.data.remote.movieLists.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApi {

    @GET("popular")
    fun getPopularMovies(
        @Query("page") page:Int
    ) : MovieListResponse

}