package com.example.moviepedia.domain

import androidx.paging.PagingData
import com.example.moviepedia.data.localDb.movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PagingRepository {

    fun getAllMovies(coroutineScope: CoroutineScope) : Flow<PagingData<MovieEntity>>

}