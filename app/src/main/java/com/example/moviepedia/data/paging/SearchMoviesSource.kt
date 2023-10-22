package com.example.moviepedia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviepedia.components.toMovieEntity
import com.example.moviepedia.data.remote.MoviesApi
import com.example.moviepedia.domain.model.MovieEntity

class SearchMoviesSource(
    private val moviesApi: MoviesApi,
    private val query : String
) : PagingSource<Int, MovieEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPage = params.key ?: 1
            val movies = moviesApi.searchMovies(query = query, page = nextPage)
            val data = movies.results.map {
                it.toMovieEntity()
            }
            LoadResult.Page(
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movies.page < movies.total_pages) movies.page.plus(1) else null,
                data = data
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition
    }
}