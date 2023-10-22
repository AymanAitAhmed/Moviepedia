package com.example.moviepedia.domain

import androidx.paging.PagingData
import com.example.moviepedia.domain.model.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PagingRepository {

    fun getPopularMovies(coroutineScope: CoroutineScope) : Flow<PagingData<MovieEntity>>

    fun getUpComingMovies(coroutineScope: CoroutineScope) : Flow<PagingData<MovieEntity>>

    fun getTopRatedMovies(coroutineScope: CoroutineScope) : Flow<PagingData<MovieEntity>>

    fun getNowPlayingMovies(coroutineScope: CoroutineScope) : Flow<PagingData<MovieEntity>>

    fun searchMovies(coroutineScope: CoroutineScope, query : String) : Flow<PagingData<MovieEntity>>

}