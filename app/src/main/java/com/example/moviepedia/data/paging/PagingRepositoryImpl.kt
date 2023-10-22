package com.example.moviepedia.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviepedia.domain.model.MovieEntity
import com.example.moviepedia.data.remote.MoviesApi
import com.example.moviepedia.domain.PagingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : PagingRepository {
    override fun getPopularMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { PopularMoviesSource(moviesApi) }
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }

    override fun getUpComingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { UpComingMoviesSource(moviesApi) }
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }

    override fun getTopRatedMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { TopRatedMoviesSource(moviesApi) }
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }

    override fun getNowPlayingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { NowPlayingMoviesSource(moviesApi) }
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }

    override fun searchMovies(coroutineScope: CoroutineScope,query : String): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { SearchMoviesSource(moviesApi,query) }
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }
}