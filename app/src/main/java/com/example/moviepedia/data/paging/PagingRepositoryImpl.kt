package com.example.moviepedia.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviepedia.data.localDb.MovieDatabase
import com.example.moviepedia.data.localDb.movie.MovieEntity
import com.example.moviepedia.data.remote.MoviesApi
import com.example.moviepedia.domain.PagingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PagingRepositoryImpl @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val moviesApi: MoviesApi
) : PagingRepository {
    override fun getAllMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { movieDatabase.movieDao.pagingSource() }
        return Pager(
            config = PagingConfig(20),
            remoteMediator = MovieRemoteMediator(
                movieDatabase = movieDatabase,
                moviesApi = moviesApi
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }
}