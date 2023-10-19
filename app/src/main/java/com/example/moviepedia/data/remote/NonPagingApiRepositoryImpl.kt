package com.example.moviepedia.data.remote

import com.example.moviepedia.components.emptyMovieDetailsResponse
import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse
import com.example.moviepedia.domain.NonPagingApiRepository
import javax.inject.Inject

class NonPagingApiRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : NonPagingApiRepository {
    override suspend fun getMovieDetails(id: Int): MovieDetailsResponse {
        return try {
            moviesApi.getMovieDetails(id)
        } catch (e : Exception){
            emptyMovieDetailsResponse.copy(error = e.message)
        }
    }
}