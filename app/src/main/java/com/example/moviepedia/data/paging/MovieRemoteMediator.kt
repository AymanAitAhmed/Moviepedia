package com.example.moviepedia.data.paging
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.example.moviepedia.components.toMovieEntity
//import com.example.moviepedia.data.localDb.MovieDatabase
//import com.example.moviepedia.domain.model.MovieEntity
//import com.example.moviepedia.data.localDb.remotekeys.MovieRemoteKeys
//import com.example.moviepedia.data.remote.MoviesApi
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//@OptIn(ExperimentalPagingApi::class)
//class MovieRemoteMediator @Inject constructor(
//    private val movieDatabase: MovieDatabase,
//    private val moviesApi: MoviesApi
//) : RemoteMediator<Int, MovieEntity>() {
//
//    private val movieDao = movieDatabase.movieDao
//    private val remoteKeysDao = movieDatabase.remoteKeys
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MovieEntity>
//    ): MediatorResult {
//        return try {
//            val currentPage = when (loadType) {
//                LoadType.REFRESH -> {
//                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                    remoteKeys?.nextPage?.minus(1) ?: 1
//                }
//                LoadType.PREPEND -> {
//                    val remoteKeys = getRemoteKeyForFirstItem(state)
//                    val prevPage = remoteKeys?.prevPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null
//                        )
//                    prevPage
//                }
//                LoadType.APPEND -> {
//                    val remoteKeys = getRemoteKeyForLastItem(state)
//                    val nextPage = remoteKeys?.nextPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null
//                        )
//                    nextPage
//                }
//            }
//
//            val movieResponse = moviesApi.getPopularMovies(currentPage)
//            val endOfPaginationReached = movieResponse.page == movieResponse.total_pages
//            val prevPage = if (movieResponse.page == 1)null else movieResponse.page.minus(1)
//            val nextPage = if (endOfPaginationReached) null else movieResponse.page.plus(1)
//
//            movieDatabase.withTransaction {
//                if (loadType == LoadType.REFRESH){
//                    movieDao.clearAll()
//                    remoteKeysDao.clearAll()
//                }
//                //println(movieResponse.results.toString())
//                val keys = movieResponse.results.map {movie ->
//                    MovieRemoteKeys(id = movie.id, prevPage = prevPage, nextPage = nextPage)
//                }
//
//
//                remoteKeysDao.upsertAll(keys)
//                movieDao.upsertAll(movieResponse.results.map {
//                    it.toMovieEntity()
//                })
//            }
//            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(
//        state: PagingState<Int, MovieEntity>
//    ): MovieRemoteKeys? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                remoteKeysDao.getRemoteKey(id)
//            }
//        }
//    }
//        private suspend fun getRemoteKeyForFirstItem(
//            state: PagingState<Int, MovieEntity>
//        ): MovieRemoteKeys? {
//            return state.pages.firstOrNull {
//                it.data.isNotEmpty()
//            }?.data?.firstOrNull()?.let {movieEntity ->
//                remoteKeysDao.getRemoteKey(movieEntity.id)
//            }
//        }
//
//
//    private suspend fun getRemoteKeyForLastItem(
//        state: PagingState<Int, MovieEntity>
//    ): MovieRemoteKeys? {
//        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
//            ?.let { movieEntity ->
//                remoteKeysDao.getRemoteKey(movieEntity.id)
//            }
//    }
//
//}