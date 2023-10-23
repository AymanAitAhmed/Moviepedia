package com.example.moviepedia.presentation.movie_list_template

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.moviepedia.components.Screens
import com.example.moviepedia.domain.model.MovieEntity

@Composable
fun MovieList(
    layoutType: Int,
    list: LazyPagingItems<MovieEntity>,
    navController: NavController
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = layoutType == 0,
            enter = slideInHorizontally(animationSpec = tween(700, 80)),
            exit = slideOutHorizontally(animationSpec = tween(600), targetOffsetX = { it / 2 })
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    count = list.itemCount
                ) {
                    val movie = list[it]
                    movie?.let { movie ->
                        SmallMovieCard(
                            imageURL = movie.poster_path ?: "",
                            title = movie.title ?: "",
                            language = movie.original_language ?: "",
                            rating = movie.vote_average?.toFloat() ?: 0.0f,
                            ratingCount = movie.vote_count ?: 0,
                            onClick = {
                                navController.navigate("${Screens.MovieDetailsScreen.route}/${movie.id}")
                            }
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = layoutType != 0,
            enter = slideInHorizontally(animationSpec = tween(700, 80)),
            exit = slideOutHorizontally(animationSpec = tween(0), targetOffsetX = { it / 2 })
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = list.itemCount) {
                    val movie = list[it]
                    movie?.let {
                        LargeMovieCard(
                            imageURL = movie.poster_path ?: "",
                            title = movie.title ?: "",
                            date = movie.release_date ?: "",
                            genresIds = movie.genre_ids ?: listOf(),
                            language = movie.original_language ?: "",
                            rating = movie.vote_average?.toFloat() ?: 0.0f,
                            ratingCount = movie.vote_count ?: 0,
                            onClick = {
                                navController.navigate("${Screens.MovieDetailsScreen.route}/${movie.id}")
                            }
                        )

                    }
                }
            }
        }
    }

}
