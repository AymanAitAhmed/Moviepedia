package com.example.moviepedia.data.remote

import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse
import com.example.moviepedia.data.remote.movieLists.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDAyMzRiMjk5YjNlMjBkMjk4ZDdmZTY3MzBlNzA5OSIsInN1YiI6IjY1MGI0NjdiMGQ1ZDg1MDBmZGI3YzIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NmwS8QTYU39HGFm8zNjDtIjPrwAetwkf9bsg7KAm3g0")
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("page") page:Int
    ) : MovieListResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDAyMzRiMjk5YjNlMjBkMjk4ZDdmZTY3MzBlNzA5OSIsInN1YiI6IjY1MGI0NjdiMGQ1ZDg1MDBmZGI3YzIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NmwS8QTYU39HGFm8zNjDtIjPrwAetwkf9bsg7KAm3g0")
    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page:Int
    ) : MovieListResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDAyMzRiMjk5YjNlMjBkMjk4ZDdmZTY3MzBlNzA5OSIsInN1YiI6IjY1MGI0NjdiMGQ1ZDg1MDBmZGI3YzIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NmwS8QTYU39HGFm8zNjDtIjPrwAetwkf9bsg7KAm3g0")
    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page:Int
    ) : MovieListResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDAyMzRiMjk5YjNlMjBkMjk4ZDdmZTY3MzBlNzA5OSIsInN1YiI6IjY1MGI0NjdiMGQ1ZDg1MDBmZGI3YzIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NmwS8QTYU39HGFm8zNjDtIjPrwAetwkf9bsg7KAm3g0")
    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page:Int
    ) : MovieListResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NDAyMzRiMjk5YjNlMjBkMjk4ZDdmZTY3MzBlNzA5OSIsInN1YiI6IjY1MGI0NjdiMGQ1ZDg1MDBmZGI3YzIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NmwS8QTYU39HGFm8zNjDtIjPrwAetwkf9bsg7KAm3g0")
    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id : Int
    ):MovieDetailsResponse

}