package com.example.moviepedia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviepedia.components.toMovieEntity
import com.example.moviepedia.data.localDb.movie.MovieEntity
import com.example.moviepedia.data.remote.MoviesApi

class TopRatedMoviesSource(
    private val moviesApi: MoviesApi
) : PagingSource<Int, MovieEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPage = params.key ?: 1
            val movies = moviesApi.getTopRatedMovies(nextPage)

            LoadResult.Page(
                prevKey = if (nextPage == 1) null else nextPage-1,
                nextKey = if (movies.page < movies.total_pages) movies.page.plus(1) else null,
                data = movies.results.map {
                    it.toMovieEntity()
                }
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return 1
    }
}